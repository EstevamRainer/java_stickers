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
        HttpClient client = HttpClient.newHttpClient();
        URI endereco = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        for (var i = 0; i < 10; i++) {
            Map<String,String> filme = listaDeFilmes.get(i);
            String imagemUrl = filme.get("image");
            InputStream inputStream = new URL(imagemUrl).openStream();
            String titulo = filme.get("title");
            String nomeArquivo = titulo + ".png";
            var geradora = new geradorDeFirugrinhas();
            geradora.cria(inputStream, nomeArquivo);
            System.out.println("🤳 \u001b[1m \u001b[33m \u001b[44m Poster:\u001b[m " + filme.get("image"));
            System.out.println("📖 \u001b[1m \u001b[33m \u001b[41m Título:\u001b[m " + filme.get("title"));
            System.out.println("🌟 \u001b[1m \u001b[33m \u001b[43m Nota:\u001b[m " + filme.get("imDbRating"));
            System.out.print("    ");
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroDeEstrelas = (int) classificacao;
            for (var n = 1; n <= numeroDeEstrelas; n++){
                System.out.print("⭐");
            }
            System.out.println();
        }
    }
}
