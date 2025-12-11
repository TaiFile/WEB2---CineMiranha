// src/hooks/useNearestTheater.ts
import { useState, useEffect } from 'react';
import { Theater, Address } from '../types/Theater';
import { addressService } from  '../services/addressesService';
import { theaterService } from '../services/theaterService';
import { nominatimService } from '../services/nominatimService';
import { calculateDistance } from '../utils/distanceUtils';
import { formatFullAddress } from '../utils/addressUtils';

interface UseNearestTheaterReturn {
  nearestTheater: Theater | null;
  allTheaters: Theater[];
  loading: boolean;
  error: string | null;
}

// ✅ Export nomeado
export const useNearestTheater = (
  userLatitude?: number,
  userLongitude?: number
): UseNearestTheaterReturn => {
  const [nearestTheater, setNearestTheater] = useState<Theater | null>(null);
  const [allTheaters, setAllTheaters] = useState<Theater[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (!userLatitude || !userLongitude) {
      setLoading(false);
      return;
    }

    const fetchNearestTheater = async () => {
      try {
        setLoading(true);
        console.log('🔵 Iniciando busca de theaters...');

        // 1. Busca todos os endereços e theaters em paralelo
        const [addresses, theaters] = await Promise.all([
          addressService.getAllAddresses(),
          theaterService.getAllTheaters()
        ]);

        console.log(`✅ ${addresses.length} endereços e ${theaters.length} theaters encontrados`);

        // 2. Verifica se há coordenadas em cache
        const cachedCoords = localStorage.getItem('addressesCoordinates');
        let addressesWithCoords: Address[] = [];

        if (cachedCoords) {
          try {
            const cached: Address[] = JSON.parse(cachedCoords);
            const cacheValid = cached.every(c => 
              addresses.some(a => a.id === c.id)
            );
            
            if (cacheValid && cached.length === addresses.length) {
              addressesWithCoords = cached;
              console.log('✅ Usando coordenadas do cache');
            } else {
              console.log('⚠️ Cache desatualizado');
            }
          } catch (err) {
            console.log('⚠️ Cache inválido');
          }
        }

        // 3. Se não tem cache válido, geocodifica
        if (addressesWithCoords.length === 0) {
          console.log('🔵 Geocodificando endereços...');
          
          addressesWithCoords = await Promise.all(
            addresses.map(async (address, index) => {
              try {
                if (index > 0) {
                  await new Promise(resolve => setTimeout(resolve, 1100));
                }
                
                const fullAddress = formatFullAddress(address);
                console.log(`🔍 Geocodificando: ${fullAddress}`);
                
                const coords = await nominatimService.getCoordinatesFromAddress(fullAddress);
                
                console.log(`✅ ${address.city}: ${coords.latitude}, ${coords.longitude}`);
                
                return {
                  ...address,
                  latitude: coords.latitude,
                  longitude: coords.longitude,
                };
              } catch (err) {
                console.error(`❌ Erro ao geocodificar ${address.city}:`, err);
                return address;
              }
            })
          );

          const validCoords = addressesWithCoords.filter(a => a.latitude && a.longitude);
          if (validCoords.length > 0) {
            localStorage.setItem('addressesCoordinates', JSON.stringify(validCoords));
            console.log(`✅ ${validCoords.length} coordenadas salvas no cache`);
          }
        }

        // 4. Mapeia theaters com endereços
        const theatersWithAddress = theaters.map(theater => {
          const address = addressesWithCoords.find(a => a.id === theater.addressId);
          return {
            ...theater,
            address
          };
        });

        // 5. Calcula distância
        const theatersWithDistance = theatersWithAddress
          .filter(t => t.address?.latitude && t.address?.longitude)
          .map(theater => ({
            ...theater,
            distance: calculateDistance(
              userLatitude,
              userLongitude,
              theater.address!.latitude!,
              theater.address!.longitude!
            )
          }))
          .sort((a, b) => a.distance! - b.distance!);

        console.log(`✅ ${theatersWithDistance.length} theaters com distância`);

        setAllTheaters(theatersWithDistance);
        
        if (theatersWithDistance.length > 0) {
          setNearestTheater(theatersWithDistance[0]);
          console.log(`🎯 Mais próximo: ${theatersWithDistance[0].name} (${theatersWithDistance[0].distance?.toFixed(1)}km)`);
        }

        setError(null);
      } catch (err) {
        console.error('❌ Erro:', err);
        setError('Erro ao buscar cinemas próximos');
      } finally {
        setLoading(false);
      }
    };

    fetchNearestTheater();
  }, [userLatitude, userLongitude]);

  return { nearestTheater, allTheaters, loading, error };
};
