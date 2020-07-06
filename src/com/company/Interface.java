package com.company;

// Пример работы со стандартной моделью таблицы JTable

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;
import javax.tools.Tool;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Interface extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table1;
    private int x = 1;
    private int number = 1;

    private void removeColumn(int index, JTable myTable) {
        System.out.println(index);
        int nRow = myTable.getRowCount();
        int nCol = myTable.getColumnCount() - 1;
        Object[][] cells = new Object[nRow][nCol];
        String[] names = new String[nCol];

        for (int j = 0; j < nCol; j++) {
            if (j < index) {
                names[j] = myTable.getColumnName(j);
                for (int i = 0; i < nRow; i++) {
                    cells[i][j] = myTable.getValueAt(i, j);
                }
            } else {
                names[j] = myTable.getColumnName(j);
                for (int i = 0; i < nRow; i++) {
                    cells[i][j] = myTable.getValueAt(i, j + 1);
                }
            }
        }

        tableModel = new DefaultTableModel(cells, names);
        myTable.setModel(tableModel);
        number--;
    }

    public Interface(String[] args) {
        super("Пример использования TableModel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создание стандартной модели
        tableModel = new DefaultTableModel();
        table1 = new JTable(tableModel);
        table1.setRowHeight(200);
        table1.getTableHeader().setReorderingAllowed(false);
        table1.setColumnSelectionAllowed(true);
        JPanel gui = new JPanel(new GridLayout(0, 1, 10, 10));
        gui.setBorder(new EmptyBorder(20, 30, 20, 30));


        Insets margin = new Insets(20, 150, 20, 150);
        JButton b = new JButton("Ручная очередь");
        JButton v = new JButton("Встроенная очередь");
        b.setMargin(margin);
        gui.add(b);
        gui.add(v);

        JFrame f = new JFrame("Centered Single Column of Buttons");
        f.add(gui);

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();


        f.pack();

        f.setMinimumSize(f.getSize());

        f.setLocationRelativeTo(null);

        f.setVisible(true);
        System.out.println(b.getSize());
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                f.setVisible(false);
                setVisible(true);
                x = 0;
            }
        });
        v.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                f.setVisible(false);
                setVisible(true);
            }
        });
        JButton add3 = new JButton("Из txt");
        add3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer[][] dv;
                    JFileChooser jfc = new JFileChooser(".");
                    jfc.setDialogType(JFileChooser.OPEN_DIALOG);
                    if (jfc.showOpenDialog(Interface.this) != JFileChooser.APPROVE_OPTION)
                        return;
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    File file = new File(path);
                    Scanner scanner = null;
                    scanner = new Scanner(file);
                    String s;
                    ArrayList<Integer[]> kek = new ArrayList<>();
                    ArrayList<Integer> ama1 = new ArrayList<>();
                    while (scanner.hasNextLine()) {
                        s = scanner.nextLine();
                        String lol[] = s.split(" ");
                        for (int i = 0; i < lol.length; i++) {
                            ama1.add(Integer.parseInt(lol[i]));
                        }
                        Integer[] array1 = new Integer[ama1.size()];
                        ama1.toArray(array1);
                        kek.add(array1);
                        ama1.clear();
                    }
                    dv = new Integer[kek.size()][kek.get(0).length];
                    for (int i = 0; i < kek.size(); i++) {
                        for (int j = 0; j < kek.get(0).length; j++) {
                            dv[i][j] = kek.get(i)[j];
                        }

                    }
                    for (int g = 0; g < dv.length; g++) {
                        if (table1.getRowCount() == 0) {
                            Integer lol2[] = new Integer[table1.getRowCount()];
                            for (int i = 0; i < lol2.length; i++) {
                                lol2[i] = 0;
                            }
                            tableModel.addColumn("Стылбец", lol2);
                        }
                        // Вставка новой строки после выделенной
                        String[] lol = new String[table1.getRowCount() + 1];
                        for (int i = 0; i < lol.length; i++) {
                            lol[i] = "0";
                        }
                        tableModel.addRow(lol);
                    }
                    for (int k = 0; k < dv[0].length - 1; k++) {
                        Integer lol2[] = new Integer[table1.getRowCount()];
                        for (int i = 0; i < lol2.length; i++) {
                            lol2[i] = 0;
                        }
                        tableModel.addColumn("Стылбец", lol2);
                    }
                    for (int g = 0; g < dv.length; g++) {
                        for (int f = 0; f < dv[0].length; f++) {
                            tableModel.setValueAt(dv[g][f], g, f);
                        }
                    }
                } catch (Exception v) {
                    System.out.println("Введите нормальное имя");
                }
            }
        });
        JButton add2 = new JButton("Добавить ячейку");
        add2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                if (table1.getRowCount() == 0) {
                    Integer lol3[] = new Integer[table1.getRowCount()];
                    for (int i = 0; i < lol3.length; i++) {
                        lol3[i] = 0;
                    }
                    tableModel.addRow(lol3);
                }
                int idx = table1.getSelectedRow();
                Integer lol2[] = new Integer[table1.getRowCount()];
                for (int i = 0; i < lol2.length; i++) {
                    lol2[i] = 0;
                }
                tableModel.addColumn("#" + Integer.toString(number));
                number++;
            }
        });
        JButton add5 = new JButton("Вычислить");
        add5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[][] dv2 = new int[table1.getRowCount()][table1.getColumnCount()];
                for (int i = 0; i <= table1.getRowCount() - 1; i++) {
                    for (int j = 0; j <= table1.getColumnCount() - 1; j++) {
                        dv2[i][j] = Integer.parseInt(String.valueOf(table1.getValueAt(i, j)));
                    }
                }
                if (x == 0) {
                    String s = "";
                    ArrayList<Integer> helpuni = Realization.kek(dv2[0]);
                    for (int i=0; i<helpuni.size();i++){
                        if (i==helpuni.size()-1){
                            s = s +helpuni.get(i);
                        }else {
                            s = s + +helpuni.get(i) + ", ";
                        }
                    }
                    JOptionPane.showMessageDialog(null,s);
                } else {
                    String s = "";
                    ArrayList<Integer> helpuni = RealizationTwo.kek(dv2[0]);
                    for (int i=0; i<helpuni.size();i++){
                        if (i==helpuni.size()-1){
                            s = s +helpuni.get(i);
                        }else {
                            s = s + +helpuni.get(i) + ", ";
                        }
                    }
                    JOptionPane.showMessageDialog(null,s);
                }
            }
        });
        JButton add6 = new JButton("Запись в txt");
        add6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(".");
                jfc.setDialogType(JFileChooser.OPEN_DIALOG);
                if (jfc.showOpenDialog(Interface.this) != JFileChooser.APPROVE_OPTION)
                    return;
                String path = jfc.getSelectedFile().getAbsolutePath();
                path=path+".txt";
                File file2 = new File(path);
                try {
                    PrintStream pw = new PrintStream(file2);
                    int[][] dv2 = new int[table1.getRowCount()][table1.getColumnCount()];
                    for (int i = 0; i <= table1.getRowCount() - 1; i++) {
                        for (int j = 0; j <= table1.getColumnCount() - 1; j++) {
                            dv2[i][j] = Integer.parseInt(String.valueOf(table1.getValueAt(i, j)));
                        }
                    }
                    for (int g = 0; g < dv2.length; g++) {
                        for (int f = 0; f < dv2[0].length; f++) {
                            pw.print(dv2[g][f] + " ");
                        }
                        pw.println();
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        // Создание кнопки удаления строки таблицы
        JButton remove = new JButton("Удалить");
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                // Удаление выделенной строки
                try {
                    removeColumn(table1.getSelectedColumn(), table1);
                }catch (Exception exp){
                    JOptionPane.showMessageDialog(null, "Добавьте ячейку!");
                }
            }
        });
        // Создание таблицы на основе модели данных
        // Определение высоты строки

        // Формирование интерфейса
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table1));
        getContentPane().add(contents);

        JPanel buttons = new JPanel();
        buttons.add(add2);
        buttons.add(add3);
        buttons.add(add5);
        buttons.add(remove);
        getContentPane().add(buttons, "South");
        // Вывод окна на экран
        setSize(900, 300);
        setLocationRelativeTo(null);
    }
}
