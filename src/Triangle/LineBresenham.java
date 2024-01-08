package Triangle;

import java.awt.*;

public class LineBresenham
{
    int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;
    public void Bresenham(int x0, int y0, int x1, int y1, int splittingNx, int splittingNy,Graphics g)
    {
        dx = x1 - x0; //проекция на ось икс
        dy = y1 - y0; //проекция на ось игрек

        incx = sign(dx);
        /*
         * Определяем, в какую сторону нужно будет сдвигаться. Если dx < 0, т.е. отрезок идёт
         * справа налево по иксу, то incx будет равен -1.
         */
        incy = sign(dy);
        /*
         * Аналогично. Если рисуем отрезок снизу вверх -
         * это будет отрицательный сдвиг для y (иначе - положительный).
         */
        dx = Math.abs(dx);
        dy = Math.abs(dy);

        if (dx > dy) //определяем наклон отрезка:
        {
             // Если dx > dy, то значит отрезок "вытянут" вдоль оси икс, т.е. он скорее длинный, чем высокий.
            pdx = incx;
            pdy = 0;
            es = dy;
            el = dx; //тогда в цикле будем двигаться по x
        }
        else //случай, когда прямая скорее "высокая", чем длинная, т.е. вытянута по оси y
        {
            pdx = 0;
            pdy = incy;
            es = dx;
            el = dy; //тогда в цикле будем двигаться по y
        }
        x = x0;
        y = y0;
        err = el/2;
        g.fillRect(splittingNx*x,splittingNy*y,splittingNx,splittingNy);
        for (int t = 0; t < el; t++) //идём по всем точкам, начиная со второй и до последней
        {
            err = err - es;
            if (err < 0)
            {
                err += el;
                x += incx;//сдвинуть прямую (сместить вверх или вниз, если цикл проходит по иксам)
                y += incy;//или сместить влево-вправо, если цикл проходит по y
            }
            else
            {
                x += pdx;//продолжить тянуть прямую дальше, т.е. сдвинуть влево или вправо, если
                y += pdy;//цикл идёт по иксу; сдвинуть вверх или вниз, если по y
            }

            g.fillRect(splittingNx*x,splittingNy*y,splittingNx,splittingNy);
        }
    }
    private int sign (int x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
        //возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
    }
    public void Bresenham2(int x0, int y0, int x1, int y1, int splittingNx, int splittingNy,Graphics g)
    {
        int deltax = Math.abs(x1 - x0);
        int deltay = Math.abs(y1 - y0);
        int error = 0;
        int deltaerr = (deltay + 1);
        int y = y0;
        int diry = y1 - y0;
        if (diry > 0)
            diry = 1;
        if (diry < 0)
            diry = -1;
        for (int i = x0; i < x1; i++)
        {
            g.fillRect(splittingNx*i,splittingNy*y,splittingNx,splittingNy);
            error = error + deltaerr;
            if (error >= (deltax + 1)) {
                y = y + diry;
                error = error - (deltax + 1);
            }
        }
    }
}
