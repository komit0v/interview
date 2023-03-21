import org.junit.Before;
import org.junit.Test;

public class ClientTest {
    private Client client;

    @Before
    public void setup(){
        this.client = new Client();
    }

    @Test
    public void testGetClients(){
        client.getClients();
    }
}
