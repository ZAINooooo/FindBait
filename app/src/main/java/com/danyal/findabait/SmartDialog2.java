package com.danyal.findabait;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SmartDialog2 {
    private Dialog dialog;
    private TextView titleTV, subTitleTv,tvOK,tvCancel;
    private LinearLayout okButtonTv, cancelButtonTV;
    //private View separator;
    private SmartDialogClickListener2 okButtonClickListener;
    private SmartDialogClickListener2 cancelButtonClickListener;
    private boolean isNegativeBtnHide;


    public SmartDialog2(Context context, String title, String subTitle, Typeface titleFont,
                        Typeface subtitleFont, boolean isCancelable, boolean isNegativeBtnHide) {

        this.isNegativeBtnHide=isNegativeBtnHide;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_dialog2);
        dialog.setCancelable(isCancelable);

        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initDialogViw();
        setTitle(title);
        setSubtitle(subTitle);
        setSubTitleFont(subtitleFont);
        setTitleFont(titleFont);
        initClickEvents();

    }

    public void setPositive(String okLabel, SmartDialogClickListener2 listener) {
        this.okButtonClickListener = listener;
        this.dismiss();
        setPositiveLabel(okLabel);
    }

    public void setNegative(String cancelLabel, SmartDialogClickListener2 listener) {
        if (listener != null) {
            this.cancelButtonClickListener = listener;
            this.dismiss();
            setNegativeLabel(cancelLabel);
        }
    }

    public void show() {
        if (isNegativeBtnHide) {
            cancelButtonTV.setVisibility(View.GONE);
            //separator.setVisibility(View.GONE);
        }
        dialog.show();
    }

    public void setTitle(String title) {
        titleTV.setText(title);
    }

    public void setSubtitle(String subtitle) {
        subTitleTv.setText(subtitle);
    }

    private void setPositiveLabel(String positive) {
        tvOK.setText(positive);
    }

    private void setNegativeLabel(String negative) {
        tvCancel.setText(negative);
    }

    private void setSubTitleFont(Typeface appleFont) {
        if (appleFont != null) {
//            title_lbl.setTypeface(appleFont);
            subTitleTv.setTypeface(appleFont);
            tvOK.setTypeface(appleFont);
            tvCancel.setTypeface(appleFont);
        }
    }

    private void setTitleFont(Typeface appleFont) {
        if (appleFont != null) {
            titleTV.setTypeface(appleFont);
        }
    }

    public void dismiss() {
        dialog.dismiss();
    }

    //positive and negative button initialise here
    private void initClickEvents() {
        okButtonTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (okButtonClickListener != null) {
                    okButtonClickListener.onClick(SmartDialog2.this);
                }
            }
        });
        cancelButtonTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cancelButtonClickListener != null) {
                    cancelButtonClickListener.onClick(SmartDialog2.this);
                }
            }
        });
    }

    //init all view here
    private void initDialogViw() {
        titleTV = dialog.findViewById(R.id.tv1);
        subTitleTv = dialog.findViewById(R.id.tv2);
        okButtonTv = dialog.findViewById(R.id.btnDialogOk);
        cancelButtonTV = dialog.findViewById(R.id.btnDialogCancel);
        //separator = dialog.findViewById(R.id.separatorView);
        tvOK = dialog.findViewById(R.id.tvok);
        tvCancel = dialog.findViewById(R.id.tvCan);
    }
}
