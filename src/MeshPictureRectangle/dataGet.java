package MeshPictureRectangle;
import java.io.*;

public class dataGet
{
    private final String FILE = "C:\\Users\\nikit\\IdeaProjects\\TestWindowPixel\\src\\MeshPictureRectangle\\fileInfo\\data.txt";
    private double[][] nodes;
    private int[][] elems;
    private  int nElems;
    public void getInfo()
    {
        try(FileWriter writer = new FileWriter(FILE, false))
        {
            // запись всей строки
            String text = "Hello Gold!";
            writer.write(text);
            writer.append('\n');

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
    public void setInfo(int t)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE)))  {
            try {
                String valueN = reader.readLine();
                int nNodes = Integer.parseInt(valueN);
                nodes = new double[nNodes][2];
                for (int i=0; i<nNodes;i++)
                {
                    String value2 = reader.readLine();
                    for ( int j=0;j<2;j++)
                    {
                        String[] s = value2.split(" ");
                        nodes[i][j] = Double.parseDouble(s[j]);
                    }
                }
                String valueE = reader.readLine();
                nElems = Integer.parseInt(valueE);
                elems = new int[nElems][t]; // Было 3
                for (int i=0; i<nElems;i++)
                {
                    String value2 = reader.readLine();
                    for ( int j=0;j<t;j++)  // Было 3
                    {
                        String[] s = value2.split(" ");
                        elems[i][j] = Integer.parseInt(s[j]);
                    }
                }


            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public int[][] getElems()
    {
        return elems;
    }
    public double[][] getNodes()
    {
        return nodes;
    }
    public int nElems()
    {
        return nElems;
    }

}
