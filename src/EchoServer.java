import EchoApp.EchoPOA;

public class EchoServer extends EchoPOA {
    @Override
    public String PIK_OtworzSesje() {
        return "PIK_OtworzSesje";
    }

    @Override
    public String PIK_SprawdzSerwer() {
        return "PIK_SprawdzSerwer";
    }
}
