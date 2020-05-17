package FallingSand;

import java.awt.*;

public class Sandlab
{
    public static void main(String[] args)
    {
        Sandlab lab = new Sandlab(120, 80);
        lab.run();
        ClearBoard();
    }
    //Test
    // add constants for particle types here
    public static final int EMPTY = 0;
    public static final int CLEARBOARD = 1;
    public static final int METAL = 2;
    public static final int SAND = 3;
    public static final int WATER = 4;
    public static final int LAVA = 5;
    public static final int OBSIDIAN = 6;
    // do not add any more fields
    private static int[][] grid;
    private SandDisplay display;

    public Sandlab(int numRows, int numCols)
    {
        grid = new int[numRows][numCols];
        String[] names;
        names = new String[7];
//		names[EMPTY] = "Empty";
        names[CLEARBOARD] = "Clear Board";
        names[METAL] = "Metal";
        names[SAND] = "Sand";
        names[WATER] = "Water";
        names[LAVA] = "Lava";
        names[OBSIDIAN] = "Obsidian";
        display = new SandDisplay("Falling Sand", numRows, numCols, names);

    }

    // called when the user clicks on a location using the given tool
    private void locationClicked(int row, int col, int tool)
    {
        if (grid[row][col] == EMPTY)
            grid[row][col] = tool;
    }


    public static void ClearBoard()
    {
        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[0].length; c++)
            {
                grid[r][c] = EMPTY;
            }
        }
    }
    // copies each element of grid into the display
    public void updateDisplay()
    {
        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[0].length; c++)
            {
                if (grid[r][c] == EMPTY)
                {
                    Color color = new Color(0, 0, 0);
                    display.setColor(r, c, color);
                }
                // METAL COLOR
                else if (grid[r][c] == METAL)
                {
                    Color color = new Color(128, 128, 128);
                    display.setColor(r, c, color);
                }
                // SAND COLOR
                else if (grid[r][c] == SAND)
                {
                    Color color = new Color(194, 178, 128);
                    display.setColor(r, c, color);
                }
                // WATER COLOR
                else if (grid[r][c] == WATER)
                {
                    Color color = new Color(0, 119, 190);
                    display.setColor(r, c, color);
                }
                // OBSIDIAN COLOR
                else if (grid[r][c] == OBSIDIAN)
                {
                    Color color = new Color(75,0,130);
                    display.setColor(r, c, color);
                }

                // LAVA COLOR (Multi-Colored)
                else if (grid[r][c] == LAVA)
                {
                    double MultiLAVA = Math.random();

                    if (MultiLAVA <= .2)
                    {
                        Color color = new Color(255, 40, 0);
                        display.setColor(r, c, color);
                    }
                    if (MultiLAVA > .2 && MultiLAVA <= .4)
                    {
                        // different  lava colors
                        Color color = new Color(255, 36, 0);
                        display.setColor(r, c, color);
                    }
                    if (MultiLAVA > .4 && MultiLAVA <= .6)
                    {
                        Color color = new Color(255, 191, 0);
                        display.setColor(r, c, color);
                    }
                    if (MultiLAVA > .6 && MultiLAVA <= .8)
                    {
                        Color color = new Color(229, 83, 0);
                        display.setColor(r, c, color);
                    }
                    if (MultiLAVA > .8)
                    {
                        Color color = new Color(255, 43, 0);
                        display.setColor(r, c, color);
                    }

                }
            }
        }
    }

    // called repeatedly.
// causes one random particle to maybe do something.
    public void step()
    {
        int r = (int) (Math.random() * grid.length - 1);
        int c = (int) (Math.random() * grid[0].length);
// SAND FUNCTIONS
        if (grid[r][c] == SAND && grid[r + 1][c] == EMPTY)
        {
            grid[r + 1][c] = SAND;
            grid[r][c] = EMPTY;

        }
        if (grid[r][c] == WATER && grid[r + 1][c] == EMPTY)
        {
            grid[r + 1][c] = WATER;
            grid[r][c] = EMPTY;
        }
        if (grid[r][c] == SAND && grid[r + 1][c] == WATER)
        {
            grid[r + 1][c] = SAND;
            grid[r][c] = WATER;
        }
        if(grid[r][c] == SAND && grid[r+1][c] == LAVA)
        {
            grid[r + 1][c] = SAND;
            grid[r][c] = LAVA;
        }

        else if (grid[r][c] == CLEARBOARD)
        {
            ClearBoard();
        }
// WATER FUNCTIONS
        else if (grid[r][c] == WATER)
        {
            if (grid[r + 1][c] == EMPTY)
            {
                grid[r + 1][c] = WATER;
                grid[r][c] = EMPTY;
            }
            else if (c > 0 && c < grid[0].length - 1 && grid[r][c - 1] == EMPTY && grid[r][c + 1] == EMPTY)
            {
// randomly goes from left and to the right, 0.5 is the halfway point.
                if (Math.random() <= 0.5)
                {
                    grid[r][c - 1] = WATER;
                    grid[r][c] = EMPTY;
                }
                else
                {
                    grid[r][c + 1] = WATER;
                    grid[r][c] = EMPTY;
                }
            }
            else if (c < grid[0].length - 1 && grid[r][c + 1] == EMPTY)
            {
                grid[r][c + 1] = WATER;
                grid[r][c] = EMPTY;
            }
            else if (c > 0 && grid[r][c - 1] == EMPTY)
            {
                grid[r][c - 1] = WATER;
                grid[r][c] = EMPTY;
            }
        }
//LAVA FUNCTIONS
        else if (grid[r][c] == LAVA)
        {
            if (grid[r + 1][c] == EMPTY)
            {
                grid[r + 1][c] = LAVA;
                grid[r][c] = EMPTY;

            }
            else if (grid[r + 1][c] == SAND)
            {
                grid[r + 1][c] = LAVA;
                grid[r][c] = EMPTY;

            }
            else if (grid[r + 1][c] == WATER)
            {
                grid[r + 1][c] = LAVA;
                grid[r][c] = EMPTY;

            }
            else if (c > 0 && c < grid[0].length - 1 && grid[r][c - 1] == EMPTY && grid[r][c + 1] == EMPTY)
            {
// randomly goes from left and to the right, 0.5 is the halfway point.
                if (Math.random() <= 0.5)
                {
                    grid[r][c - 1] = LAVA;
                    grid[r][c] = EMPTY;
                }
                else
                {
                    grid[r][c + 1] = LAVA;
                    grid[r][c] = EMPTY;
                }
            }
            else if (c < grid[0].length - 1 && grid[r][c + 1] == EMPTY)
            {
                grid[r][c + 1] = LAVA;
                grid[r][c] = EMPTY;
            }
            else if (c > 0 && grid[r][c - 1] == EMPTY)
            {
                grid[r][c - 1] = LAVA;
                grid[r][c] = EMPTY;
            }
            // OBSIDIAN FUNCTIONS
            if (grid[r][c] == OBSIDIAN && grid[r + 1][c] == EMPTY)
            {
                grid[r + 1][c] = OBSIDIAN;
                grid[r][c] = EMPTY;
            }
            // obsidian forms when water touches lava
            if (grid[r+1][c]==WATER && c>=0 && c<= grid[0].length-1)
            {
                grid[r][c] = OBSIDIAN;
                grid[r+1][c] = OBSIDIAN;
            }
            if (grid[r-1][c]==WATER && c>=0 && c<= grid[0].length-1)
            {
                grid[r-1][c] = OBSIDIAN;
                grid[r][c] = OBSIDIAN;
            }
        }


    }

    // do not modify
    public void run()
    {
        while (true)
        {
            for (int i = 0; i < display.getSpeed(); i++)
                step();
            updateDisplay();
            display.repaint();
            display.pause(1); // wait for redrawing and for mouse
            int[] mouseLoc = display.getMouseLocation();
            if (mouseLoc != null) // test if mouse clicked
                locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
        }
    }
}