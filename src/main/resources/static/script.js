const API_URL = 'http://localhost:8080/api/produtos';

document.addEventListener("DOMContentLoaded", listarProdutos);

function listarProdutos() {
    fetch(API_URL)
        .then(response => response.json())
        .then(produtos => {
            const tbody = document.getElementById('tabelaProdutos');
            tbody.innerHTML = '';
            produtos.forEach(p => {
                tbody.innerHTML += `
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.nome}</td>
                        <td>R$ ${p.preco.toFixed(2)}</td>
                        <td>${p.quantidade}</td>
                        <td>
                            <button class="btn-edit" onclick="editar(${p.id}, '${p.nome}', ${p.preco}, ${p.quantidade})">Editar</button>
                            <button class="btn-delete" onclick="deletar(${p.id})">Excluir</button>
                        </td>
                    </tr>
                `;
            });
        });
}

function salvar() {
    const id = document.getElementById('produtoId').value;
    const produto = {
        nome: document.getElementById('nome').value,
        preco: parseFloat(document.getElementById('preco').value),
        quantidade: parseInt(document.getElementById('quantidade').value)
    };

    const metodo = id ? 'PUT' : 'POST';
    if(id) produto.id = id;

    fetch(API_URL, {
        method: metodo,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(produto)
    }).then(() => {
        limparFormulario();
        listarProdutos();
    });
}

function deletar(id) {
    if(confirm('Deseja realmente excluir?')) {
        fetch(`${API_URL}/${id}`, { method: 'DELETE' })
            .then(() => listarProdutos());
    }
}

function editar(id, nome, preco, quantidade) {
    document.getElementById('produtoId').value = id;
    document.getElementById('nome').value = nome;
    document.getElementById('preco').value = preco;
    document.getElementById('quantidade').value = quantidade;
}

function limparFormulario() {
    document.getElementById('produtoId').value = '';
    document.getElementById('nome').value = '';
    document.getElementById('preco').value = '';
    document.getElementById('quantidade').value = '';
}