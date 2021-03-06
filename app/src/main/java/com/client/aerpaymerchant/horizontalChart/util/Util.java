package com.client.aerpaymerchant.horizontalChart.util;

import android.graphics.Color;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by user on 16/01/2018.
 */

public class Util {
    public static String formatMoney(Double value, Locale locale) {
        Locale aux = locale != null ? locale : Locale.getDefault();

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(aux);

        BigDecimal bd = new BigDecimal(value);
        bd = bd.round(new MathContext(2, RoundingMode.HALF_UP));

        return numberFormat.format(bd.doubleValue());
    }

    public static int darken(int color, double fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        red = darkenColor(red, fraction);
        green = darkenColor(green, fraction);
        blue = darkenColor(blue, fraction);
        int alpha = Color.alpha(color);

        return Color.argb(alpha, red, green, blue);
    }

    private static int darkenColor(int color, double fraction) {
        return (int)Math.max(color - (color * fraction), 0);
    }
}
