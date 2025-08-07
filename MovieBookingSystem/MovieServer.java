import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.io.InputStream;

public class MovieServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8086), 0); // Changed port
        server.createContext("/", new MyHandler());
        server.setExecutor(null); 
        server.start();
        System.out.println("Server started at http://localhost:8086");
    }

    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String path = t.getRequestURI().getPath();
            if ("/".equals(path)) path = "/index.html";
            String filePath = "WebContent" + path;

            if (Files.exists(Paths.get(filePath))) {
                byte[] bytes = Files.readAllBytes(Paths.get(filePath));
                t.sendResponseHeaders(200, bytes.length);
                OutputStream os = t.getResponseBody();
                os.write(bytes);
                os.close();
            } else {
                String response = "404 Not Found";
                t.sendResponseHeaders(404, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}