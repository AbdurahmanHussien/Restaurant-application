/** @type {import('tailwindcss').Config} */
module.exports = {
  mode: 'jit',
  darkMode: 'class',
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        amberCustom: '#ffaa00',
      },
    },
  },
  plugins: [
    require('tailwind-scrollbar-hide')
  ],
}

