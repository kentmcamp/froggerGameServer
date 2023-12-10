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
    if (command.equals("MOVEDOWN")) {
      // Parse the tokens from 'in' request
      System.out.println("MOVEDOWN");
      String outCommand = "frogger.getPosY() + Properties.FROGGER_STEP";
      out.println(outCommand);
      out.flush();
      return;
    }
    if (command.equals("MOVELEFT")) {
      // Parse the tokens from 'in' request
      System.out.println("MOVELEFT");
      String outCommand = "frogger.getPosX() - Properties.FROGGER_STEP";
      out.println(outCommand);
      out.flush();
      return;
    }
    if (command.equals("MOVERIGHT")) {
      // Parse the tokens from 'in' request
      System.out.println("MOVERIGHT");
      String outCommand = "frogger.getPosX() + Properties.FROGGER_STEP";
      out.println(outCommand);
      out.flush();
      return;
    }

    if (command.equals("GETCARS")) {
      initializeCars(4, 250, 200, 100, 0, "bike.gif");
      initializeCars(4, 380, 200, 200, 1, "car2.gif");
      initializeCars(4, 440, 200, 400, 2, "car.gif");
    }

    if (command.equals("GETLOGS")) {
      initializeLogs(4, 60, 200, 0);
      initializeLogs(4, 124, 400, 1);
      initializeLogs(4, 188, 800, 2);
    }



  }
}
