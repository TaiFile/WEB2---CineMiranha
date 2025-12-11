import { Address } from '../types/Theater';

export const formatFullAddress = (address: Address): string => {
  const parts = [
    `${address.street}, ${address.number}`,
    address.complement,
    address.neighborhood,
    address.city,
    address.state,
    address.zipCode,
    address.country
  ].filter(Boolean); 
  
  return parts.join(', ');
};

export const formatShortAddress = (address: Address): string => {
  return `${address.street}, ${address.number} - ${address.neighborhood}, ${address.city} - ${address.state}`;
};
