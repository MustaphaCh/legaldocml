package io.legaldocml.xpath.cerebro;

import io.legaldocml.akn.AknObject;

import java.util.Optional;

public class CerebroBuilder {

    private final StringBuilder builder = new StringBuilder(1024);

    private int temp = 0;

    public CerebroBuilder() {
        builder.append("public final class CerebroClass {");
        builder.append("\n\n");
        builder.append("   public java.util.Optional apply(AkomaNtoso akn) { ");
    }

    public <E extends AknObject> void append(CerebroCodeLink<E> link) {


        link.apply(this);


        System.out.println(link);



    }

    public String build() {
        builder.append("\n      return ").append(Optional.class.getName()).append(".of(tmp").append(temp-1).append(");");
        builder.append("\n   }\n}");
        return builder.toString();
    }

    public <E extends AknObject> void appendGetter(Class<E> returnClass, String getter) {

        if (temp == 0) {
            // define var
            builder.append("\n      ").append(returnClass.getName()).append(" tmp").append(temp++);

            builder.append(" = akn.").append(getter).append("();");
        } else {
            // define var
            builder.append("\n      ").append(returnClass.getName()).append(" tmp").append(temp++);

            builder.append(" = tmp").append(temp-2).append(".").append(getter).append("();");
        }


    }
}
