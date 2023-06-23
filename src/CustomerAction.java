import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerAction extends JFrame {
	public CustomerAction() {
		setSize(new Dimension(585, 400));
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(255, 250, 205));
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Create Customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerForm ca = new CustomerForm(null, null, "");
				ca.setVisible(true);
			}
		});
		btnNewButton.setBounds(185, 66, 225, 47);
		getContentPane().add(btnNewButton);
		
		JButton btnModifydeleteCustomer = new JButton("Modify/Delete Customer");
		btnModifydeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchForm sf = new SearchForm(true, false);
				sf.setVisible(true);
			}
		});
		btnModifydeleteCustomer.setBounds(185, 168, 225, 47);
		getContentPane().add(btnModifydeleteCustomer);
	}

}
