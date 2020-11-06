/*written by Tatiana Azulay*/
import java.awt.event.*;
import java.awt.*; 
import javax.swing.*;

public class Calculator extends JFrame 
{  /*fields */
 private JMenuBar menuBar;
 private JMenu view,edit,help;

 private JMenuItem close,copy,viewhelp,about;
 
 private JTextArea display, display2, display3;
 private JButton mc, mr,ms,mplus,mminus,del,ce,c,positivenegative,sqroot,divide,mult,substract,addition,percentage, xToMinusOne,equal,decimal;
 private JButton zero,one,two,three,four,five,six,seven,eight,nine;
 private double tempFirst=0.0;
 private double tempSecond=0.0;
 private double memory;
 private boolean[] operation=new boolean [4];// division, multiplication,substraction, addition
 
      
               public Calculator() //constructor
               {super("Calculator");//constructor method of superclass JFrame -creates a new, initially invisible Frame(container) with the specified title
                runMenuBar();
                runDisplay();
                runButtons();
                runParameters(); //this sets general parameters of the JFrame object
                }
                private void runMenuBar()
                {menuBar=new JMenuBar();
                view=new JMenu(" View ");
                edit=new JMenu(" Edit ");
                help= new JMenu(" Help ");
                close=new JMenuItem("Close");
                copy=new JMenuItem("Copy");
                viewhelp=new JMenuItem("View Help");
                about=new JMenuItem("About Calculator");
                
                setJMenuBar(menuBar);
                menuBar.add(view);
                menuBar.add(edit);
                menuBar.add(help);
                close.addActionListener(new ActionListener(){ //anonymous inner class because it's defined inside the calculator class without name and it is not actually defined it as a subclass.
                public void actionPerformed(ActionEvent e)   //however the compiler generates some class that implements ActionListener interface, puts actionPerformed() method inside the generated class definition, then creates an instance of that class. 
                {System.exit(0);
                }
                });
                
                copy.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {JOptionPane.showMessageDialog(null,"Not Available.","Calculator",JOptionPane.OK_OPTION);
                }
                });
                
                viewhelp.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {JOptionPane.showMessageDialog(null,"Not Available.","Calculator",JOptionPane.OK_OPTION);
                }
                });

                about.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {JOptionPane.showMessageDialog(null,"Written by Tatiana Azulay","Calculator",JOptionPane.OK_OPTION);
                }
                });
                view.add(close);
                edit.add(copy);
                help.add(viewhelp);
                help.add(about);
                }

                private void runDisplay()
                {display= new JTextArea("0");//instead of JTextField to use append() method
                display.setBounds(10,30,275,30);
                display.setEditable(false);
                display.setFont(new Font("Arial", Font.PLAIN, 22));
                add(display);
                display2=new JTextArea();
                display2.setBounds(10,10,245,20);
                display2.setEditable(false);
                display2.setFont(new Font("Arial", Font.PLAIN, 14));
                add(display2);
                display3=new JTextArea();
                display3.setBounds(255,10,30,20);
                display3.setEditable(false);
                display3.setFont(new Font("Arial", Font.PLAIN, 14));
                add(display3);

                }
                
                private void runButtons()
                {mc=new JButton("MC");     //Memory Clear sets the memory to 0
                mc.setBounds(10,70,50,50);
                mc.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {setMemory(0.0);
                display3.setText("");
                }
                });

                add(mc);
                mr=new JButton("MR");      //Memory Recall uses the number in memory, acts as if you had keyed in that number yourself 
                mr.setBounds(66,70,50,50);
                mr.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {String s=Double.toString(memory);
                if (s.endsWith(".0"))
                      display.setText(s.replace(".0",""));
                }
                });
                add(mr);
                ms=new JButton("MS");      //Memory Store puts the number on the display into the memory 
                ms.setBounds(122,70,50,50);
                ms.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {if(Double.parseDouble(display.getText())!=0)
                {memory=Double.parseDouble(display.getText());
                display3.setText("M");
                }
                }
                });
                add(ms);
                mplus=new JButton("M+");    //Memory Add takes the number on the display, adds it to the memory, and puts the result into memory
                mplus.setBounds(178,70,50,50);
                mplus.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {memory+=Double.parseDouble(display.getText());
                if (memory!=0.0)
                display3.setText("M");
                else
                display.setText("");
                }
                });

                add(mplus);
                mminus=new JButton("M-");
                mminus.setBounds(234,70,50,50);
                mminus.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {memory-=Double.parseDouble(display.getText());
                if (memory!=0.0)
                display3.setText("M");
                else
                display3.setText("");
                }
                });

                add(mminus);
                del=new JButton("del");   //deletes the last digit if it is entered incorrectly
                del.setBounds(10,125,50,50);
                del.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {if(Double.parseDouble(display.getText())>=0  && Double.parseDouble(display.getText())<=9 && !display.getText().contains("."))
                {display.setText("0");
                return;
                }
                String l=display.getText();
                display.setText(l.substring(0,l.length()-1));
                }
                });
                add(del);
                ce=new JButton("CE");             //clear entry
                ce.setBounds(66,125,50,50);
                ce.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {display.setText("0");
                }
                });

                add(ce);
                c=new JButton("C");   //clear button, clears the entire calculation
                c.setBounds(122,125,50,50);
                c.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {display.setText("0");
                display2.setText("");
                setTempFirst(0.0);
                setTempSecond(0.0);
                for (int i=0; i<=3;i++)
                operation[i]=false;
                }
                });
                add(c);
                positivenegative=new JButton("+/-");
                positivenegative.setBounds(178,125,50,50);
                positivenegative.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {if (display.getText().equalsIgnoreCase("0"))
                  return;
                display.setText(Double.toString(Double.parseDouble(display.getText())*(-1)));//Double.parseDouble(String s) converts String to Double
                if (display.getText().endsWith("0"))
                display.setText(display.getText().replace(".0",""));
                }
                });
                add(positivenegative);
                sqroot=new JButton("sqr");
                sqroot.setBounds(234,125,50,50);
                sqroot.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {display2.setText("sqrt("+display.getText()+")");
                double result=Math.sqrt(Double.parseDouble(display.getText()));
                display.setText(Double.toString(result));
                if (display.getText().endsWith(".0"))
                      display.setText(display.getText().replace(".0",""));
                }
                });
                add(sqroot);
                seven=new JButton("7");
                seven.setBounds(10,180,50,50);
                seven.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                if (display.getText().length()>20)
                return;
                if(display.getText().equalsIgnoreCase("0"))
                {display.setText("7");
                return;
                }
                display.append("7");
                }
                });
                add(seven);
                eight=new JButton("8");
                eight.setBounds(66,180,50,50);
                eight.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                if (display.getText().length()>20)
                return;
                if(display.getText().equalsIgnoreCase("0"))
                {display.setText("8");
                return;
                }
                display.append("8");
                
                }
                });
                add(eight);
                nine=new JButton("9");
                nine.setBounds(122,180,50,50);
                nine.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                if (display.getText().length()>20)
                return;
                if(display.getText().equalsIgnoreCase("0"))
                {display.setText("9");
                return;
                }
                display.append("9");
                }
                });
                add(nine);
                divide=new JButton("/");
                divide.setBounds(178,180,50,50);
                divide.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {setTempFirst(Double.parseDouble(display.getText()));//this converts string to double and stores it in tempFirst
                operation[0]=true;
                display2.setText(display.getText()+"/");
                display.setText("");
                }
                });
                add(divide);
                percentage=new JButton("%");
                percentage.setBounds(234,180,50,50);
                percentage.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {display.setText(Double.toString((Double.parseDouble(display.getText())*getTempFirst()/100)));
                display2.append(display.getText());
                }
                });
                add(percentage);
                four=new JButton("4");
                four.setBounds(10,235,50,50);
                four.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                if (display.getText().length()>20)
                return;
                if(display.getText().equalsIgnoreCase("0"))
                {display.setText("4");
                return;
                }
                display.append("4");
                }
                });

                add(four);
                five=new JButton("5");
                five.setBounds(66,235,50,50);
                five.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                if (display.getText().length()>20)
                return;
                if(display.getText().equalsIgnoreCase("0"))
                {display.setText("5");
                return;
                }
                display.append("5");
                }
                });

                add(five);
                six=new JButton("6");
                six.setBounds(122,235,50,50);
                six.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e){
                if (display.getText().length()>20)
                return;
                if(display.getText().equalsIgnoreCase("0"))
                {display.setText("6");
                return;
                }
                display.append("6");
                }
                });

                add(six);
                mult=new JButton("*");
                mult.setBounds(178,235,50,50);
                mult.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {setTempFirst(Double.parseDouble(display.getText()));//this converts string to double and stores in tempFirst
                operation[1]=true;
                display2.setText(display.getText()+"*");
                display.setText(""); 
                }
                });
                add(mult);
                xToMinusOne=new JButton("1/x");
                xToMinusOne.setBounds(234,235,50,50);
                xToMinusOne.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {display2.setText("reciproc("+display.getText()+")");
                display.setText(Double.toString(1/Double.parseDouble(display.getText())));
                }
                });
                
                add(xToMinusOne);
                one=new JButton("1");
                one.setBounds(10,290,50,50);
                one.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                if (display.getText().length()>20)
                return;
                if(display.getText().equalsIgnoreCase("0"))
                {display.setText("1");
                return;
                }
                display.append("1");
                }
                });

                add(one);
                two=new JButton("2");
                two.setBounds(66,290,50,50);
                two.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                if (display.getText().length()>20)
                return;
                if(display.getText().equalsIgnoreCase("0"))
                {display.setText("2");
                return;
                }
                display.append("2");
                }
                });

                add(two);
                three=new JButton("3");
                three.setBounds(122,290,50,50);
                three.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                if (display.getText().length()>20)
                return;
                if(display.getText().equalsIgnoreCase("0"))
                {display.setText("3");
                return;
                }
                display.append("3");
                }
                });

                add(three);
                substract=new JButton("-");
                substract.setBounds(178,290,50,50);
                substract.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {setTempFirst(Double.parseDouble(display.getText()));//this converts string to double
                operation[2]=true;
                display2.setText(display.getText()+"-");
                display.setText("");
                }
                });
                add(substract);
                equal=new JButton("=");
                equal.setBounds(234,290,50,106);
                equal.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                   {if (operation[0])
                      {setTempSecond(Double.parseDouble(display.getText()));
                      if(tempSecond==0)
                      display.setText("Cannot divide by zero");
                      else
                      display.setText(Double.toString(getTempFirst()/getTempSecond()));
                      }else if(operation[1])
                        {setTempSecond(Double.parseDouble(display.getText()));
                         display.setText(Double.toString(getTempFirst()*getTempSecond()));
                         }else if(operation[2])
                         {setTempSecond(Double.parseDouble(display.getText()));
                         display.setText(Double.toString(getTempFirst()-getTempSecond()));
                         }else if(operation[3])
                            {setTempSecond(Double.parseDouble(display.getText()));
                            display.setText(Double.toString(getTempFirst()+getTempSecond()));
                             }
                      if (display.getText().endsWith(".0"))
                      display.setText(display.getText().replace(".0",""));
                         setTempFirst(0.0);
                         setTempSecond(0.0);
                         for (int i=0; i<=3;i++)
                         operation[i]=false;
                         display2.setText("");
                         }
                });
                add(equal);
                zero=new JButton("0");
                zero.setBounds(10, 345,106,50);
                zero.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                if (display.getText().length()>20)
                return;
                if(display.getText().equalsIgnoreCase("0"))
                {display.setText("0");
                return;
                }
                display.append("0");
                }
                });

                add(zero);
                decimal=new JButton(".");
                decimal.setBounds(122,345,50,50);
                decimal.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {if(display.getText().contains("."))
                    return;
                display.append(".");
                }
                });
                add(decimal);
                addition=new JButton("+");
                addition.setBounds(178,345,50,50);
                addition.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent e)
                {setTempFirst(Double.parseDouble(display.getText()));//this converts string to double
                operation[3]=true;
                display2.setText(display.getText()+"+");
                display.setText("");
                }
                });
                add(addition);
                
                }
                private void runParameters()
                {setDefaultCloseOperation(EXIT_ON_CLOSE);
                setSize(300,450);
                setResizable(false);
                setLayout(null);
                setLocationRelativeTo(null);//center of the screen
                setVisible(true);
                }
                
                public double getTempFirst()
                {return tempFirst;
                }
                 public void setTempFirst(double tempFirst)
                {this.tempFirst=tempFirst;
                }
                 public double getTempSecond()
                {return tempSecond;
                }
                 public void setTempSecond(double tempSecond)
                {this.tempSecond=tempSecond;
                }
                public double getMemory()
                {return memory;
                }
                 public void setMemory(double memory)
                {this.memory=memory;
                }


                   
 public static void main(String []args)
      {
      try
      {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }catch(Exception e)
      {System.out.println("Could not load System look.");
      }
      new Calculator();//creates a new instance of calculator object
      }              
          
}