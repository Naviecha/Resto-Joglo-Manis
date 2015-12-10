import javax.swing.JWindow;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Resto extends JWindow {

	Image img = Toolkit.getDefaultToolkit().getImage("loading.gif");
	ImageIcon imgicon = new ImageIcon(img);

	public Resto() {
		try {

			setSize(128, 128);
			setLocationRelativeTo(null);
			show();
			Thread.sleep(5000);
			dispose();
			new Controller();
		} catch (Exception exception) {
			javax.swing.JOptionPane.showMessageDialog((java.awt.Component) null, "Error" + exception.getMessage(),
					"Error:", javax.swing.JOptionPane.DEFAULT_OPTION);
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

	public static void main(String[] args) {
		Resto tes = new Resto();

	}
}