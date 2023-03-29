import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class geradorDeFirugrinhas {

    void cria(InputStream inputStream, String nomeArquivo) throws IOException {
        //InputStream inputStream = new FileInputStream(new File("image/shawshank.jpg"));
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        Graphics2D copia = (Graphics2D) novaImagem.getGraphics();
        copia.drawImage(imagemOriginal, null, 0, 0);
        var fonte = new Font("Impact", Font.BOLD, 100);
        copia.setFont(fonte);
        copia.setColor(Color.red);
        String texto = "Omagaaaa";
        FontMetrics fontMetrics = copia.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, copia);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura - larguraTexto) / 2;
        int posicaoTextoY = novaAltura - 100;
        copia.drawString(texto, posicaoTextoX, posicaoTextoY);
        FontRenderContext renderContext = copia.getFontRenderContext();
        var textLayout = new TextLayout(texto, fonte, renderContext);
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = copia.getTransform();
        transform.translate(posicaoTextoX, posicaoTextoY);
        copia.setTransform(transform);
        var outlineStroke = new BasicStroke(largura * 0.004f);
        copia.setStroke(outlineStroke);
        copia.setColor(Color.BLUE);
        copia.draw(outline);
        copia.setClip(outline);

        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));
        


    }
}


