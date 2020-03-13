package com.ihsinformatics.covid.views;

import android.view.View;

import com.ihsinformatics.covid.model.WidgetData;


public abstract class Widget {
    public abstract View getView();
    public abstract WidgetData getValue();
    public abstract boolean isValid();
    public abstract boolean hasAttribute();
    public abstract Widget hideView();
    public abstract Widget showView();
    public abstract Widget addHeader(String headerText);
    public abstract Integer getAttributeTypeId();
    public abstract boolean isViewOnly();
}
