package br.com.org.store.testes;

import br.com.org.store.dao.CategoriaDao;
import br.com.org.store.dao.ProdutoDao;
import br.com.org.store.modelo.Categoria;
import br.com.org.store.modelo.Produto;
import br.com.org.store.util.JPAUtil;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
        cadastrarProduto();
        Long id = 1l;
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(id);
        System.out.println(p.getPreco());

        List<Produto> produtos = produtoDao.buscarPorNomeDaCategoria("Celulares");
        produtos.forEach(p2 -> System.out.println(p2.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoPorNome("Xiaomi Redmi");
        System.out.println("Preço do produto: R$ " +precoDoProduto);
    }

    public static void cadastrarProduto() {
        Categoria celulares = new Categoria("Celulares");
        Produto celular = new Produto("Xiaomi Redmi", "Bom produto", new BigDecimal("800.00"), celulares);

        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }

}
