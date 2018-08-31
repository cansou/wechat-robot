package com.im.ui.wechatui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.synth.*;
class NewClass2 extends JPanel {
    private final JTabbedPane tabs;
    public NewClass2() {
        super(new BorderLayout());
        //famfamfam.com: Mini Icons>http://www.famfamfam.com/lab/icons/mini/
        ImageIcon icon = new ImageIcon("C:\\Users\\Arthur\\Desktop\\90.png");
        JButton b = new ToolBarButton(icon);
        b.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                tabs.addTab("qwerqwer", new JLabel("yetyet"));
            }
        });
        b.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
        UIManager.put("TabbedPane.tabAreaInsets", getButtonPaddingTabAreaInsets(b));
        tabs = new ClippedTitleTabbedPane();
        tabs.addTab("asdfasd", new JLabel("456746"));
        tabs.addTab("1234123", new JScrollPane(new JTree()));
        tabs.addTab("6780969", new JLabel("zxcvzxc"));
        final JPanel p = new JPanel();
        p.setLayout(new OverlayLayout(p));
        b.setAlignmentX(0.0f);
        b.setAlignmentY(0.0f);
        tabs.setAlignmentX(0.0f);
        tabs.setAlignmentY(0.0f);
        p.add(b);
        p.add(tabs);
        JMenuBar menubar = new JMenuBar();
        JMenu m1 = new JMenu("Tab");
        m1.add(new AbstractAction("removeAll") {
            @Override public void actionPerformed(ActionEvent e) {
                tabs.removeAll();
            }
        });
        menubar.add(m1);
        menubar.add(new JMenu("Dummy1"));
        menubar.add(new JMenu("Dummy2"));
        add(menubar, BorderLayout.NORTH);
        add(p);
        setPreferredSize(new Dimension(320, 180));
    }
    public Insets getButtonPaddingTabAreaInsets(JButton b) {
        Insets ti = getTabInsets();
        Insets ai = getTabAreaInsets();
        FontMetrics fm = b.getFontMetrics(b.getFont());
        int tih = b.getPreferredSize().height-fm.getHeight()-ti.top-ti.bottom-ai.bottom;
        return new Insets(Math.max(ai.top, tih), b.getPreferredSize().width+ai.left, ai.bottom, ai.right);
    }
    private static Insets getTabInsets() {
        Insets i = UIManager.getInsets("TabbedPane.tabInsets");
        if(i!=null) {
            return i;
        }else{
            JTabbedPane tabbedPane = new JTabbedPane();
            SynthStyle style = SynthLookAndFeel.getStyle(tabbedPane, Region.TABBED_PANE_TAB);
            SynthContext context = new SynthContext(tabbedPane, Region.TABBED_PANE_TAB, style, SynthConstants.ENABLED);
            return style.getInsets(context, null);
        }
    }
    private static Insets getTabAreaInsets() {
        Insets i = UIManager.getInsets("TabbedPane.tabAreaInsets");
        if(i!=null) {
            return i;
        }else{
            JTabbedPane tabbedPane = new JTabbedPane();
            SynthStyle style = SynthLookAndFeel.getStyle(tabbedPane, Region.TABBED_PANE_TAB_AREA);
            SynthContext context = new SynthContext(tabbedPane, Region.TABBED_PANE_TAB_AREA, style, SynthConstants.ENABLED);
            return style.getInsets(context, null);
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                createAndShowGUI();
            }
        });
    }
    public static void createAndShowGUI() {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("TabbedPaneWithButton");
        frame.setMinimumSize(new Dimension(256, 80));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new NewClass2());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
class ToolBarButton extends JButton {
    public ToolBarButton(ImageIcon icon) {
        super(icon);
        setFocusable(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent me) {
                setContentAreaFilled(true);
            }
            @Override public void mouseExited(MouseEvent me) {
                setContentAreaFilled(false);
            }
        });
    }
}
class ClippedTitleTabbedPane extends JTabbedPane {
    public ClippedTitleTabbedPane() {
        super();
    }
    public ClippedTitleTabbedPane(int tabPlacement) {
        super(tabPlacement);
    }
    private Insets getTabInsets() {
        Insets i = UIManager.getInsets("TabbedPane.tabInsets");
        if(i!=null) {
            return i;
        }else{
            SynthStyle style = SynthLookAndFeel.getStyle(this, Region.TABBED_PANE_TAB);
            SynthContext context = new SynthContext(this, Region.TABBED_PANE_TAB, style, SynthConstants.ENABLED);
            return style.getInsets(context, null);
        }
    }
    private Insets getTabAreaInsets() {
        Insets i = UIManager.getInsets("TabbedPane.tabAreaInsets");
        if(i!=null) {
            return i;
        }else{
            SynthStyle style = SynthLookAndFeel.getStyle(this, Region.TABBED_PANE_TAB_AREA);
            SynthContext context = new SynthContext(this, Region.TABBED_PANE_TAB_AREA, style, SynthConstants.ENABLED);
            return style.getInsets(context, null);
        }
    }
    @Override public void doLayout() {
        int tabCount  = getTabCount();
        if(tabCount==0) return;
        Insets tabInsets     = getTabInsets();
        Insets tabAreaInsets = getTabAreaInsets();
        Insets insets = getInsets();
        int areaWidth = getWidth() - tabAreaInsets.left - tabAreaInsets.right - insets.left - insets.right;
        int tabWidth  = 0; // = tabInsets.left + tabInsets.right + 3;
        int gap       = 0;
        switch(getTabPlacement()) {
          case LEFT: case RIGHT:
            tabWidth = areaWidth / 4;
            gap = 0;
            break;
          case BOTTOM: case TOP: default:
            tabWidth = areaWidth / tabCount;
            gap = areaWidth - (tabWidth * tabCount);
        }
        if(tabWidth>80) {
            tabWidth = 80;
            gap = 0;
        }
        // "3" is magic number @see BasicTabbedPaneUI#calculateTabWidth
        tabWidth = tabWidth - tabInsets.left - tabInsets.right - 3;
        for(int i=0;i<tabCount;i++) {
            JComponent l = (JComponent)getTabComponentAt(i);
            if(l==null) break;
            l.setPreferredSize(new Dimension(tabWidth+(i<gap?1:0), l.getPreferredSize().height));
        }
        super.doLayout();
    }
    @Override public void insertTab(String title, Icon icon, Component component, String tip, int index) {
        super.insertTab(title, icon, component, tip==null?title:tip, index);
        JLabel label = new JLabel(title, JLabel.CENTER);
        //Dimension dim = label.getPreferredSize();
        //Insets tabInsets = getTabInsets();
        //label.setPreferredSize(new Dimension(0, dim.height+tabInsets.top+tabInsets.bottom));
        setTabComponentAt(index, label);
    }
}
