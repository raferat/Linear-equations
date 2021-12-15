
public class Matice
{

  private double[][] array;

  final int width;
  final int height;

  public Matice(int x , int y)
  {
    this.width = x;
    this.height = y;
    array = new double[x][y];
  }

  public Matice(int size)
  {
    this(size , size);
  }

  public Matice()
  {
    this(2_000);
  }

  public Matice(double[][] mat)
  {
    array = mat;
    width = mat.length;
    height = mat[0].length;
  }

  public Matice(Matice m)
  {
	array = new double [m.array.length][1];
	for ( int i = 0 ; i < array.length ; i ++ )
	{
		array[i] = m.array[i].clone();
	}
    width = m.width;
    height = m.height;
  }

  public void set(int x , int y , double value)
  {
    array[x][y] = value;
  }
  
  public double get(int x , int y)
  {
    return array[x][y];
  }

  public double[][] array()
  {
    return array;
  }

  @Override
  public String toString()
  {
    String s = "";
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < height; y++)
      {
        s += array[y][x] + ", ";
      }
      s += "\n";
    }
    
    return s.substring(0, s.length() - 3);
  }

  
  
  public void print()
  {
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < height; y++)
      {
        System.out.print(array[x][y] + ", ");
      }
      System.out.println();
    }
  }

  public Matice multiply(Matice c)
  {
    if (this.height != c.width)
    {
      throw new RuntimeException("Matice se nedaji nasobit");
    }

    double[][] matice = new double[this.width][c.height];

    for (int i = 0; i < width; i++)
    {
      for (int j = 0; j < c.height; j++)
      {
        double result = 0;
        //result += get(i,j) * c . get ( j , i );
        for (int r = 0; r < c.width; r++)
        {
          result += get(i , r) * c.get(r , j);
        }

        matice[i][j] = result;
      }

    }

    return new Matice(matice);
  }

  public Matice transpozice()
  {
    Matice m = new Matice(height , width);
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < height; y++)
      {
        m.set(y , x , get(x , y));
      }
    }

    return m;
  }

  public Matice rowChange(int row1 , int row2)
  {
    double[][] arr = array.clone();

    for (int x = 0; x < width; x++)
    {
      if (x == row1)
      {
        arr[x] = array[row2].clone();
      }
      else if (x == row2)
      {
        arr[x] = array[row1].clone();
      }
      else
      {
        arr[x] = array[x].clone();
      }
    }

    return new Matice(arr);
  }

  public double determinant()
  {

    if (width != height)
    {
      throw new RuntimeException("Height != width");
    }
    else if (width == 1)
    {
      return array[0][0];
    }
    else if (width == 2)
    {
      return array[0][0] * array[1][1] - array[0][1] * array[1][0];
    }

    double result = 0d;

    if (width == 3)
    {
      for (int i = 0; i < width; i++)
      {
        result += array[0][i % width] * array[1][(i + 1) % width] * array[2][(i + 2) % width];
      }
      for (int i = 0; i < width; i++)
      {
        int newWidth = width - 1;
        result -= array[0][newWidth - (i % width)] * array[1][newWidth - ((i + 1) % width)] * array[2][newWidth - ((i + 2) % width)];
      }
    }
    else
    {
      Matice adj = new Matice(1 , this.width);
      int sign = 1;
      for (int i = 0; i < width; i++)
      {
        Matice m = fillExceptFor(0 , i , this);
        adj.set(0 , i , sign * m.determinant());

        sign *= -1;
      }

      for (int j = 0; j < width; j++)
      {
        double uu = get(0,j) * adj.get(0 , j);

        result += uu;
      }

    }

    return result;
  }

  public Matice adjungovana()
  {
    if (width != height)
    {
      throw new RuntimeException("Height != width");
    }
    else if (width == 1)
    {
      return new Matice(new double[][]
      {
        {
          0
        }
      });
    }

    Matice src = this.transpozice();
    
    Matice m = new Matice(height);

    int sign = 1;
    
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < width; y++)
      {
    	sign = ( (x + y) % 2 == 0) ? 1 : -1;
        Matice tmp = Matice.fillExceptFor(x , y , src);
        m.set(x , y , sign * tmp.determinant());
      }
    }
    return m;

  }

  public static Matice fillExceptFor(int column , int row , Matice source)
  {
    Matice matice = new Matice(source.height - 1);
    int k = 0, l = 0;
    for (int x = 0; x < source.width; x++)
    {
      if (x == column)
      {
        k--;
      }

      for (int y = 0; y < source.height; y++)
      {
        if (x != column && y != row)
        {
          matice.set(x + k , y + l , source.get(x , y));
        }
        else if (y == row)
        {
          l--;
        }
      }
      l = 0;
    }

    return matice;
  }
  
  public Matice inverse()
  {
    if ( width != height )
      throw new RuntimeException("Height != width");
    
    
    Matice mat = new Matice(width);
    
    Matice adj = adjungovana();
    
    double det = determinant();
    
    if ( det == 0 )
      return this;
    
    for ( int i = 0 ; i < width ; i ++ )
      for ( int j = 0 ; j < height ; j ++ )
        mat . set(i, j, adj.get(i, j) / det);
    
    return mat;
  }
}
