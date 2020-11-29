package com.github.willspader.DailyMovie;

import com.github.willspader.DailyMovie.client.caller.OMDBCaller;
import com.github.willspader.DailyMovie.dto.HistoricDTO;
import com.github.willspader.DailyMovie.dto.OmdbDTO;
import com.github.willspader.DailyMovie.service.HistoricService;
import com.github.willspader.DailyMovie.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DailyMovieApplication extends JFrame {

	@Autowired
	private RandomService randomService;

	@Autowired
	private HistoricService historicService;

	@Autowired
	private OMDBCaller omdbCaller;

	public DailyMovieApplication() {
		initUI();
	}

	private void initUI() {
		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel1 = makePanel();
		tabbedPane.addTab("Procurar", panel1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = makePanel();
		tabbedPane.addTab("Histórico", panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		// Add the tabbed pane to this panel.
		add(tabbedPane);

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		// Panel #1 Content
		createFirstTabContent(panel1);

		// Panel #2 Content
		List<JTextField> secondTabContent = createSecondTabContent(panel2);

		tabbedPane.addChangeListener(e -> {
			// if it is historic tab, fill text fields with last suggestions
			if (tabbedPane.getSelectedIndex() == 1) {
				List<HistoricDTO> lastSuggestions = historicService.findLastSuggestions(5);

				for (int i = 0; i < lastSuggestions.size(); i++) {
					secondTabContent.get(i).setText(lastSuggestions.get(i) != null ? lastSuggestions.get(i).getName() : "");
				}
			}
		});
	}

	private void createFirstTabContent(JComponent component) {
		JLabel title = new JLabel("Título: ");
		JLabel year = new JLabel("Ano: ");
		JLabel actors = new JLabel("Atores: ");
		JLabel director = new JLabel("Diretor: ");
		JLabel plot = new JLabel("Sinopse: ");
		JLabel awards = new JLabel("Prêmios: ");
		JLabel rating = new JLabel("Pontuação IMDB: ");
		JLabel type = new JLabel("Tipo: ");

		JTextField titleText = new JTextField();
		titleText.setPreferredSize(new Dimension(200, 24));
		titleText.setEditable(false);

		JTextField yearText = new JTextField();
		yearText.setPreferredSize(new Dimension(200, 24));
		yearText.setEditable(false);

		JTextField actorsText = new JTextField();
		actorsText.setPreferredSize(new Dimension(400, 24));
		actorsText.setEditable(false);

		JTextField directorText = new JTextField();
		directorText.setPreferredSize(new Dimension(200, 24));
		directorText.setEditable(false);

		JTextField plotText = new JTextField();
		plotText.setPreferredSize(new Dimension(600, 24));
		plotText.setEditable(false);

		JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		BoundedRangeModel brm = plotText.getHorizontalVisibility();
		scrollBar.setModel(brm);
		scrollBar.setPreferredSize(new Dimension(100, 15));
		scrollBar.setMaximumSize(new Dimension(100, 15));

		JTextField awardsText = new JTextField();
		awardsText.setPreferredSize(new Dimension(600, 24));
		awardsText.setEditable(false);

		JTextField ratingText = new JTextField();
		ratingText.setPreferredSize(new Dimension(200, 24));
		ratingText.setEditable(false);

		JTextField typeText = new JTextField();
		typeText.setPreferredSize(new Dimension(200, 24));
		typeText.setEditable(false);

		component.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 5, 10);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_END;
		component.add(title, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		component.add(titleText, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		component.add(year, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		component.add(yearText, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_END;
		component.add(actors, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_START;
		component.add(actorsText, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_END;
		component.add(director, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		component.add(directorText, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LINE_END;
		component.add(plot, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LINE_START;
		component.add(plotText, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.CENTER;
		component.add(scrollBar, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.LINE_END;
		component.add(awards, gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.LINE_START;
		component.add(awardsText, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.LINE_END;
		component.add(rating, gbc);
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.LINE_START;
		component.add(ratingText, gbc);

		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.LINE_END;
		component.add(type, gbc);
		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.LINE_START;
		component.add(typeText, gbc);

		JButton searchBtn = new JButton("Procurar");
		searchBtn.addActionListener(e -> {
			OmdbDTO result = randomService.getRandomMovieOrTVShow();
			if (result != null) {
				titleText.setText(result.getTitle());
				yearText.setText(result.getYear());
				actorsText.setText(result.getActors());
				directorText.setText(result.getDirector());
				plotText.setText(result.getPlot());
				awardsText.setText(result.getAwards());
				ratingText.setText(result.getImdbRating());
				typeText.setText(result.getType());
			}
		});

		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		component.add(searchBtn, gbc);
	}

	private List<JTextField> createSecondTabContent(JComponent component) {
		JLabel label  = new JLabel("Últimos 5 títulos sugeridos");

		JLabel title  = new JLabel("Título: ");
		JLabel title2 = new JLabel("Título: ");
		JLabel title3 = new JLabel("Título: ");
		JLabel title4 = new JLabel("Título: ");
		JLabel title5 = new JLabel("Título: ");

		JTextField titleText = new JTextField();
		titleText.setPreferredSize(new Dimension(200, 24));
		titleText.setEditable(false);

		JTextField titleText2 = new JTextField();
		titleText2.setPreferredSize(new Dimension(200, 24));
		titleText2.setEditable(false);

		JTextField titleText3 = new JTextField();
		titleText3.setPreferredSize(new Dimension(200, 24));
		titleText3.setEditable(false);

		JTextField titleText4 = new JTextField();
		titleText4.setPreferredSize(new Dimension(200, 24));
		titleText4.setEditable(false);

		JTextField titleText5 = new JTextField();
		titleText5.setPreferredSize(new Dimension(200, 24));
		titleText5.setEditable(false);

		component.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 5, 10);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		component.add(label, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		component.add(title, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		component.add(titleText, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_END;
		component.add(title2, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_START;
		component.add(titleText2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_END;
		component.add(title3, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		component.add(titleText3, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LINE_END;
		component.add(title4, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LINE_START;
		component.add(titleText4, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.LINE_END;
		component.add(title5, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.LINE_START;
		component.add(titleText5, gbc);

		List<JTextField> jTextFields = new ArrayList<>();

		jTextFields.add(titleText);
		jTextFields.add(titleText2);
		jTextFields.add(titleText3);
		jTextFields.add(titleText4);
		jTextFields.add(titleText5);

		return jTextFields;
	}

	private JComponent makePanel() {
		return new JPanel();
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(DailyMovieApplication.class).headless(false).run(args);

		EventQueue.invokeLater(() -> {
			var ex = ctx.getBean(DailyMovieApplication.class);
			ex.setPreferredSize(new Dimension(1000, 500));
			ex.setDefaultCloseOperation(EXIT_ON_CLOSE);
			ex.pack();
			ex.setVisible(true);
		});

	}

}
