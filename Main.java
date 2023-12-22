import java.util.Optional;

class Pedido {
    private final int id;
    private final String descricao;

    public Pedido(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

}

interface RepositorioPedido {
    Optional<Pedido> buscarPorId(int id);

    void salvar(Pedido pedido);

    void excluir(int id);
}

class RepositorioPedidoImpl implements RepositorioPedido {

    private final java.util.Map<Integer, Pedido> armazenamento = new java.util.HashMap<>();

    @Override
    public Optional<Pedido> buscarPorId(int id) {
        return Optional.ofNullable(armazenamento.get(id));
    }

    @Override
    public void salvar(Pedido pedido) {
        armazenamento.put(pedido.getId(), pedido);
    }

    @Override
    public void excluir(int id) {
        armazenamento.remove(id);
    }
}


public class ExemploRepositorio {

    public static void main(String[] args) {
    
        RepositorioPedido repositorio = new RepositorioPedidoImpl();

      
        Pedido novoPedido = new Pedido(1, "Pedido de teste");

      
        repositorio.salvar(novoPedido);

      
        Optional<Pedido> pedidoRecuperado = repositorio.buscarPorId(1);

     
        if (pedidoRecuperado.isPresent()) {
            Pedido pedidoEncontrado = pedidoRecuperado.get();
            System.out.println("Pedido encontrado: " + pedidoEncontrado.getDescricao());
        } else {
            System.out.println("Pedido nao encontrado.");
        }

     
        repositorio.excluir(1);
    }
}
