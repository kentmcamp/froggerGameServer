import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class FroggerService implements Runnable {

  private Socket s;
  private Scanner in;
  private PrintWriter out;

  public FroggerService() {
    super();
  }

  public FroggerService(Socket s) {
    this.s = s;
  }

  @Override
  public void run() {
    try {
      in = new Scanner(s.getInputStream());
      out = new PrintWriter(s.getOutputStream());
      processRequest();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        s.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void processRequest() {
    while (true) {
      if (!in.hasNext()) return;

      String command = in.next();
      executeCommand(command);
    }
  }

  private void executeCommand(String command) {
    if (command.equals("MOVEUP")) {
      // Parse the tokens from 'in' request
      System.out.println("MOVEUP");

      String outCommand = "frogger.getPosY() - Properties.FROGGER_STEP";
      out.println(outCommand);
      out.flush();
      return;
    }
  }
}
