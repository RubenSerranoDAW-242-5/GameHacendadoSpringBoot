document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.querySelector('#searchInput');
    const categorySelect = document.querySelector('select[name="categoria"]');
    const gridContainer = document.getElementById('grid');

    if (searchInput && categorySelect) {
        searchInput.addEventListener('input', function () {
            const query = this.value.trim();
            const categoria = categorySelect.value;
            fetch(`/buscar?query=${query}&categoria=${categoria}`)
                .then(response => response.text())
                .then(html => {
                    gridContainer.innerHTML = html;
                    const cartas = gridContainer.querySelectorAll('.carta');
                    cartas.forEach((carta, index) => {
                        carta.style.animationDelay = `${index * 0.1}s`;
                    });
                })
                .catch(error => console.error('Error fetching search results:', error));
        });

        categorySelect.addEventListener('change', function () {
            const query = searchInput.value.trim();
            const categoria = this.value;
            fetch(`/buscar?query=${query}&categoria=${categoria}`)
                .then(response => response.text())
                .then(html => {
                    gridContainer.innerHTML = html;
                    const cartas = gridContainer.querySelectorAll('.carta');
                    cartas.forEach((carta, index) => {
                        carta.style.animationDelay = `${index * 0.1}s`;
                    });
                })
                .catch(error => console.error('Error fetching search results:', error));
        });
    }
});

