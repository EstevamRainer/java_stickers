import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // String url ="https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // var extrator = new ExtratorDeConteudoDaNasa();
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        var geradora = new geradorDeFirugrinhas();

        for (var i = 0; i <= 10; i++) {
            Conteudo conteudo = conteudos.get(i);
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";
            geradora.cria(inputStream, nomeArquivo);
            System.out.println("🤳 \u001b[1m \u001b[33m \u001b[44m Poster:\u001b[m " + conteudo.getUrlImagem());
            System.out.println("📖 \u001b[1m \u001b[33m \u001b[41m Título:\u001b[m " + conteudo.getTitulo());
            System.out.println("🌟 \u001b[1m \u001b[33m \u001b[43m Nota:\u001b[m " + conteudo.getClass());
            System.out.print("    ");
            // double classificacao = Double.parseDouble(conteudo.get("imDbRating"));
            // int numeroDeEstrelas = (int) classificacao;
            // for (var n = 1; n <= numeroDeEstrelas; n++){
            //     System.out.print("⭐");
            // }
            // System.out.println();
        }
    }
}
