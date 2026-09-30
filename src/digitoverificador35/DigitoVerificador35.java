package digitoverificador35;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DigitoVerificador35 extends JFrame {
    private JTextField txtRUT, txtNombre, txtResultado;
    private JButton btnGenerar;

    public DigitoVerificador35() {
        setTitle("⋆˙⟡ Generador de dígito verificador ⟡˙⋆");
        setSize(400, 350); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        setLayout(new BorderLayout(10, 10));
        
        getContentPane().setBackground(Color.PINK);
        
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout(20, 20));
        
        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCampos.setBackground(Color.PINK);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setOpaque(true);
        
        lblNombre.setBackground(Color.PINK);
        panelCampos.add(lblNombre);
        
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);
        
        
        JLabel lblRUT = new JLabel("RUT sin dígito verificador:");
        lblRUT.setOpaque(true);
        
        lblRUT.setBackground(Color.PINK);
        panelCampos.add(lblRUT);
        
        txtRUT = new JTextField();
        panelCampos.add(txtRUT);
        
        
        JLabel lblResultado = new JLabel("Resultado:");
        lblResultado.setOpaque(true);
        lblResultado.setBackground(Color.PINK);
        panelCampos.add(lblResultado);
        
        txtResultado = new JTextField();
        txtResultado.setEditable(false);
        panelCampos.add(txtResultado);
        
        
        add(panelCampos, BorderLayout.CENTER);
        
        
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(Color.PINK);
        
        
        btnGenerar = new JButton("Generar Dígito Verificador");
        btnGenerar.setBackground(new Color(255, 182, 193)); // Rosa más claro
        btnGenerar.setForeground(Color.BLACK);
        panelBoton.add(btnGenerar);
        
       
        add(panelBoton, BorderLayout.SOUTH);
        
        // ActionListener
        btnGenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarDigito();
            }
        });
    }

    private char calcularDigitoVerificador(String rut) {
        char[] digitos = rut.replaceAll("[^0-9]", "").toCharArray();
        int[] multiplicadores = {2, 3, 4, 5, 6, 7};
        int suma = 0;
        
        for (int i = digitos.length - 1, j = 0; i >= 0; i--, j++) {
            int multiplicador = multiplicadores[j % multiplicadores.length];
            suma += (digitos[i] - '0') * multiplicador;
        }
        
        int resultado = 11 - (suma % 11);
       
        if (resultado == 11) {
            return '0';
        } else if (resultado == 10) {
            return 'K';
        } else {
            return (char) (resultado + '0');
        }
    }

    private void generarDigito() {
        String rut = txtRUT.getText().trim();
        String nombre = txtNombre.getText().trim();
        
        if (rut.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un RUT");
            return;
        }
        
        try {
            char digito = calcularDigitoVerificador(rut);
            
            if (nombre.isEmpty()) {
                txtResultado.setText("Dígito verificador: " + digito);
            } else {
                txtResultado.setText( nombre + ", tu dígito verificador es " + digito);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en el cálculo. Verifica el RUT");
        }
    }

    public static void main(String[] args) {
        new DigitoVerificador35();
    }
}