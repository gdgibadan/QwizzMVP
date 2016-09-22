package com.ibadan.gdg.qwizzmvp.util;

import android.databinding.BindingAdapter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by Efe on 22/09/2016.
 */
public class BindingHelper {

    @BindingAdapter("font")
    public static void setTextTypeface(TextView view, String fontDesc) {

        Typeface old = view.getTypeface();
        int style = Typeface.NORMAL;
        if (old != null) style = old.getStyle();

        String path = parseDescription(fontDesc, style);
        if (path == null) return;

        view.setPaintFlags(view.getPaintFlags() | Paint.ANTI_ALIAS_FLAG);
        view.setTypeface(Fonts.get(view.getContext(), path));
    }

    private static String parseDescription(String desc, int style) {
        if (desc == null) return null;

        switch (style) {

            case Typeface.NORMAL:
                if (desc.equalsIgnoreCase("mus")) return "Museo-Sans.otf";
                if (desc.equalsIgnoreCase("ubl")) return "Ubuntu-Light.ttf";
                if (desc.equalsIgnoreCase("ubr")) return "Ubuntu-Regular.ttf";
                if (desc.equalsIgnoreCase("ubm")) return "Ubuntu-Medium.ttf";
                break;

            case Typeface.BOLD:
                if (desc.equalsIgnoreCase("mus")) return "Museo-Sans.otf";
                if (desc.equalsIgnoreCase("ubl")) return "Ubuntu-Regular.ttf";
                if (desc.equalsIgnoreCase("ubr")) return "Ubuntu-Bold.ttf";
                if (desc.equalsIgnoreCase("ubm")) return "Ubuntu-Bold.ttf";
                break;

            case Typeface.ITALIC:
                if (desc.equalsIgnoreCase("mus")) return "Museo-Sans.otf";
                if (desc.equalsIgnoreCase("ubl")) return "Ubuntu-LightItalic.ttf";
                if (desc.equalsIgnoreCase("ubr")) return "Ubuntu-Italic.ttf";
                if (desc.equalsIgnoreCase("ubm")) return "Ubuntu-MediumItalic.ttf";
                break;

            case Typeface.BOLD_ITALIC:
                if (desc.equalsIgnoreCase("mus")) return "Museo-Sans.otf";
                if (desc.equalsIgnoreCase("ubl")) return "Ubuntu-Italic.ttf";
                if (desc.equalsIgnoreCase("ubm")) return "Ubuntu-BoldItalic.ttf";
                if (desc.equalsIgnoreCase("ubr")) return "Ubuntu-BoldItalic.ttf";
                break;
        }

        return null;
    }
}
