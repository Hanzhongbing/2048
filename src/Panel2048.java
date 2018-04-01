import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Panel2048 extends JPanel
{

    Cell[][] map = new Cell[4][4];


    JFrame jf;
    Random a = new Random();
    private int cellWidth = 90;
    private int borderWidth = 10;
    private boolean isMove = false;


    public Panel2048(JFrame jf)
    {
        this.jf = jf;
        jf.setSize(4 * cellWidth + 5 * borderWidth + 7, 4 * cellWidth + 5 * borderWidth + 28);

        StartGame();

        jf.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {

            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                isMove = false;
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_DOWN:
                        Todown();
                        break;
                    case KeyEvent.VK_UP:
                        ToUp();
                        break;
                    case KeyEvent.VK_LEFT:
                        ToLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        ToRight();
                        break;
                }


                if (HasEmpty() && isMove)
                {
                    generateNum();

                }
                Panel2048.this.repaint();


                if (isDead() && !HasEmpty())
                {
                    JOptionPane.showMessageDialog(jf, "sb,重玩吧你");
                    StartGame();
                }

            }

            @Override
            public void keyReleased(KeyEvent e)
            {

            }
        });
    }

//不能比较第二次

    public boolean HasEmpty()
    {

        boolean HasEmptyfalg = false;
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map.length; j++)
            {
                if (map[i][j].num == 0)
                {
                    HasEmptyfalg = true;
                }
            }
        }
        return HasEmptyfalg;
    }

    public boolean isDead()
    {
        boolean isDeadflag;
        isDeadflag = true;
        for (int i = 0; i < map.length - 1; i++)
        {
            for (int j = 0; j < map.length; j++)
            {
                if (map[i][j].num == map[i + 1][j].num)
                {
                    isDeadflag = false;
                }
            }
        }
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map.length - 1; j++)
            {
                if (map[i][j].num == map[i][j + 1].num)
                {
                    isDeadflag = false;
                }
            }
        }
        return isDeadflag;
    }

    public void StartGame()
    {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map.length; j++)
            {
                map[i][j] = new Cell();
            }
        }
        generateNum();
        generateNum();
        Panel2048.this.repaint();
    }

    public void generateNum()
    {
        List<Point> temp = new ArrayList<>();
        for (int i = 0; i < map.length; i++)
        {
            for (int k = 0; k < map.length; k++)
            {
                if (map[i][k].num == 0)
                {
                    temp.add(new Point(i, k));
                }
            }
        }
        Point point = temp.get(a.nextInt(temp.size()));
        map[point.x][point.y].num = 2;
    }

    private void ToRight()
    {
        for (int k = 0; k < map.length; k++)
        {
            for (int i = map.length - 1; i >= 0; i--)
            {
                if (map[i][k].num == 0) continue;
                for (int j = i - 1; j >= 0; j--)
                {
                    if (map[j][k].num != 0)
                    {
                        if (map[i][k].num == map[j][k].num)
                        {
                            map[j][k].num = 0;
                            map[i][k].num = map[i][k].num * 2;
                            i = j;
                            isMove = true;
                        }
                        break;
                    }
                }
            }

            for (int i = map.length - 1; i >= 0; i--)
            {
                if (map[i][k].num == 0)
                {
                    for (int j = i - 1; j >= 0; j--)
                    {
                        if (map[j][k].num != 0)
                        {
                            map[i][k].num = map[j][k].num;
                            map[j][k].num = 0;
                            isMove = true;
                            break;
                        }
                    }
                }

            }
        }
    }

    private void ToLeft()
    {

        for (int k = 0; k < map.length; k++)
        {
            for (int i = 0; i < map.length - 1; i++)
            {
                for (int j = i + 1; j < map.length; j++)
                {
                    if (map[j][k].num != 0)
                    {
                        if (map[i][k].num == map[j][k].num)
                        {
                            map[j][k].num = 0;
                            map[i][k].num = map[i][k].num * 2;
                            i = j;
                            isMove = true;
                        }
                        break;
                    }
                }
            }
            for (int i = 0; i < map.length - 1; i++)
            {
                if (map[i][k].num == 0)
                {
                    for (int j = i + 1; j < map.length; j++)
                    {
                        if (map[j][k].num != 0)
                        {
                            map[i][k].num = map[j][k].num;
                            map[j][k].num = 0;
                            isMove = true;
                            break;
                        }
                    }
                }

            }
        }
    }


    private void ToUp()
    {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map.length - 1; j++)
            {
                for (int k = j + 1; k < map.length; k++)
                {
                    if (map[i][k].num != 0)
                    {
                        if (map[i][j].num == map[i][k].num)
                        {
                            map[i][k].num = 0;
                            map[i][j].num = map[i][j].num * 2;
                            j = k;
                            isMove = true;
                        }
                        break;
                    }
                }
            }

            for (int j = 0; j < map.length - 1; j++)
            {
                if (map[i][j].num == 0)
                {
                    for (int k = j + 1; k < map.length; k++)
                    {
                        if (map[i][k].num != 0)
                        {
                            map[i][j].num = map[i][k].num;
                            map[i][k].num = 0;
                            isMove = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    private void Todown()
    {
        for (int k = 0; k < map.length; k++)
        {
            for (int i = map.length - 1; i >= 0; i--)
            {
                if (map[k][i].num == 0) continue;
                for (int j = i - 1; j >= 0; j--)
                {
                    if (map[k][j].num != 0)
                    {
                        if (map[k][i].num == map[k][j].num)
                        {
                            map[k][j].num = 0;
                            map[k][i].num = map[k][i].num * 2;
                            i = j;
                            isMove = true;
                        }
                        break;
                    }
                }
            }

            for (int i = map.length - 1; i >= 0; i--)
            {
                if (map[k][i].num == 0)
                {
                    for (int j = i - 1; j >= 0; j--)
                    {
                        if (map[k][j].num != 0)
                        {
                            map[k][i].num = map[k][j].num;
                            map[k][j].num = 0;
                            isMove = true;
                            break;
                        }
                    }
                }

            }
        }

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        HashMap<Integer, ColorPair> hashMap = new HashMap<>();
        hashMap.put(0, new ColorPair(new Color(0xcccccc), new Color(0xcccccc)));

        hashMap.put(2, new ColorPair(new Color(0xffffff), new Color(0x7cadbf)));
        hashMap.put(4, new ColorPair(new Color(0x1a7475), new Color(0x162863)));
        hashMap.put(8, new ColorPair(new Color(0x65175f), new Color(0x59001d)));
        hashMap.put(16, new ColorPair(new Color(0x7cadbf), new Color(0x070064)));
        hashMap.put(32, new ColorPair(new Color(0x070064), new Color(0x1a7475)));
        hashMap.put(64, new ColorPair(new Color(0x59001d), new Color(0xfe6969)));
        hashMap.put(128, new ColorPair(new Color(0xa9b400), new Color(0x3aaf00)));
        hashMap.put(256, new ColorPair(new Color(0xd9e100), new Color(0x65175f)));
        hashMap.put(512, new ColorPair(new Color(0x4b486f), new Color(0x070064)));
        hashMap.put(1024, new ColorPair(new Color(0x162863), new Color(0x3f6000)));
        hashMap.put(2048, new ColorPair(new Color(0x3f6000), new Color(0xfc0000)));


        super.paintComponent(g);
        g.setColor(new Color(0xd8dfd6));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setFont(new Font(null, 0, 25));

        g.setColor(new Color(0xd2bf9c));
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map.length; j++)
            {

                g.setColor(hashMap.get(map[i][j].num).groundcolor);
                g.fillRect(cellWidth * i + (i + 1) * this.borderWidth, borderWidth * (j + 1) + j * cellWidth, this.cellWidth, this.cellWidth);
            }
        }
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map.length; j++)
            {

                if (map[i][j].num != 0)
                {
                    if (map[i][j].num == 1024)
                    {
                        JOptionPane.showMessageDialog(jf, "还玩，睡觉吧你");

                    }
                    g.setColor(hashMap.get(map[i][j].num).color);
                    g.drawString(map[i][j].num + "", 30 + i * 100, 40 + j * 100);

                }
            }
        }
    }

}
