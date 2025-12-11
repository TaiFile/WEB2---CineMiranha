import React from "react";

const mapStepToLabel = {
  1: "Escolha da sessão",
  2: "Escolha seus assentos",
  3: "Escolha seus ingressos",
  4: "Escolha seus snacks",
  5: "Confirmar seu pedido",
  6: "Escolha a forma de pagamento",
  7: "Selecione o cartão",
};

const StepProgressBar = ({ currentStep = 1 }) => {
  const totalSteps = 7;
  const label = mapStepToLabel[currentStep] || "Progresso";

  return (
    <div className="rounded-lg">
      <div className="flex items-center justify-between mb-4">
        {Array.from({ length: totalSteps }).map((_, index) => {
          const stepNumber = index + 1;
          const isActive = stepNumber === currentStep;
          const isPassed = stepNumber < currentStep;

          return (
            <React.Fragment key={stepNumber}>
              <div className="flex flex-col items-center">
                <div
                  className={`w-4 h-4 rounded-sm transition-colors duration-300 ${
                    isActive
                      ? "bg-cinema-red"
                      : isPassed
                      ? "bg-cinema-darkPalette-500"
                      : "bg-cinema-light-900"
                  }`}
                />
              </div>

              {stepNumber < totalSteps && (
                <div
                  className={`flex-1 h-0.5 mx-1 transition-colors duration-300 ${
                    isPassed ? "bg-gray-400" : "bg-gray-600"
                  }`}
                />
              )}
            </React.Fragment>
          );
        })}
      </div>

      <div className="text-center">
        <h1 className="text-white text-xl font-medium">{label}</h1>
      </div>
    </div>
  );
};

export default StepProgressBar;
