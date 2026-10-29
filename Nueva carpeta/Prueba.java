import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Prueba extends JFrame {
	private final JTextField[] solicitudes = new JTextField[3];
	private final JComboBox<String>[] prioridades = new JComboBox[3];

	@SuppressWarnings("unchecked")
	public Prueba() {
		super("Formulario de necesidades sociales");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel formulario = new JPanel(new GridBagLayout());
		formulario.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(6, 6, 6, 6);
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		formulario.add(new JLabel("Necesidad social"), c);
		c.gridx = 1;
		formulario.add(new JLabel("Prioridad"), c);

		String[] opcionesPrioridad = {"Baja", "Media", "Alta"};
		for (int i = 0; i < solicitudes.length; i++) {
			c.gridy = i + 1;
			c.gridx = 0;
			c.weightx = 1;
			solicitudes[i] = new JTextField(24);
			solicitudes[i].setToolTipText("Solicitud " + (i + 1));
			formulario.add(solicitudes[i], c);

			c.gridx = 1;
			c.weightx = 0;
			prioridades[i] = new JComboBox<>(opcionesPrioridad);
			formulario.add(prioridades[i], c);
		}

		JButton enviar = new JButton("Enviar solicitudes");
		enviar.addActionListener(event -> enviarSolicitudes());
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		formulario.add(enviar, c);

		setContentPane(formulario);
		pack();
		setLocationRelativeTo(null);
	}

	private void enviarSolicitudes() {
		List<String> resumen = new ArrayList<>();
		for (int i = 0; i < solicitudes.length; i++) {
			String solicitud = solicitudes[i].getText().trim();
			if (!solicitud.isEmpty()) {
				resumen.add("• " + solicitud + " — Prioridad: " + prioridades[i].getSelectedItem());
			}
		}

		if (resumen.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese al menos una solicitud.",
					"Formulario incompleto", JOptionPane.WARNING_MESSAGE);
			return;
		}

		JOptionPane.showMessageDialog(this,
				"Solicitudes enviadas:\n" + String.join("\n", resumen),
				"Envío completado", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Prueba().setVisible(true));
	}
}
