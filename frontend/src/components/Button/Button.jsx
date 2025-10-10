function Button({ text, onClickHandler }) {
  return (
    <button
      className="bg-cinema-light-900 transition-all duration-200 hover:scale-105  text-cinema-darkPalette-900 font-bold py-2 px-4 w-full rounded-xl"
      onClick={onClickHandler}
    >
      {text}
    </button>
  );
}

export default Button;
