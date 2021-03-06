package com.example.appdemo1;

import java.lang.reflect.Field;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.RelativeLayout;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.view.Gravity;

/*Draft java code by "Lazarus Android Module Wizard" [1/8/2015 22:10:35]*/
/*https://github.com/jmpessoa/lazandroidmodulewizard*/
/*jVisualControl template*/

public class jSwitchButton extends Switch /*API 14*/ { //please, fix what GUI object will be extended!

   private long       pascalObj = 0;    // Pascal Object
   private Controls   controls  = null; // Control Class for events

   private Context context = null;
   private ViewGroup parent   = null;         // parent view
   private ViewGroup.MarginLayoutParams lparams = null;              // layout XYWH

   private OnCheckedChangeListener onClickListener ;   // click event

   private Boolean enabled  = false;           // click-touch not enabled!

   private int lparamsAnchorRule[] = new int[30];
   private int countAnchorRule = 0;
   private int lparamsParentRule[] = new int[30];
   private int countParentRule = 0;

   //private int lparamH = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
   //private int lparamW = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
   private int lparamH = 100;
   private int lparamW = 100;
   private int marginLeft = 5;
   private int marginTop = 5;
   private int marginRight = 5;
   private int marginBottom = 5;
   private int lgravity = Gravity.TOP | Gravity.START;
   private float lweight = 0;

   private boolean mRemovedFromParent = false;

   //GUIDELINE: please, preferentially, init all yours params names with "_", ex: int _flag, String _hello ...

   public jSwitchButton(Controls _ctrls, long _Self) { //Add more others news "_xxx"p arams if needed!
      super(_ctrls.activity);
      context   = _ctrls.activity;
      pascalObj = _Self;
      controls  = _ctrls;

      onClickListener = new OnCheckedChangeListener(){
         /*.*/public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {  //please, do not remove /*.*/ mask for parse invisibility!
            if (enabled) {
               controls.pOnChangeSwitchButton(pascalObj, isChecked); //JNI event onClick!
            }
         };
      };
      setOnCheckedChangeListener(onClickListener);

      lparams = new ViewGroup.MarginLayoutParams(lparamW, lparamH);     // W,H
      lparams.setMargins(marginLeft,marginTop,marginRight,marginBottom); // L,T,R,B
   } //end constructor

   public void jFree() {
      if (parent != null) { parent.removeView(this); }
      //free local objects...
      lparams = null;
      setOnClickListener(null);
   }

   private static MarginLayoutParams newLayoutParams(ViewGroup aparent, ViewGroup.MarginLayoutParams baseparams) {
      if (aparent instanceof FrameLayout) {
         return new FrameLayout.LayoutParams(baseparams);
      } else if (aparent instanceof RelativeLayout) {
         return new RelativeLayout.LayoutParams(baseparams);
      } else if (aparent instanceof LinearLayout) {
         return new LinearLayout.LayoutParams(baseparams);
      } else if (aparent == null) {
         throw new NullPointerException("Parent is null");
      } else {
         throw new IllegalArgumentException("Parent is neither FrameLayout or RelativeLayout or LinearLayout: "
                 + aparent.getClass().getName());
      }
   }

   public void SetViewParent(ViewGroup _viewgroup) {
      if (parent != null) { parent.removeView(this); }
      parent = _viewgroup;

      parent.addView(this,newLayoutParams(parent,(ViewGroup.MarginLayoutParams)lparams));
      lparams = null;
      lparams = (ViewGroup.MarginLayoutParams)this.getLayoutParams();

      mRemovedFromParent = false;
   }

   public void RemoveFromViewParent() {
      if (!mRemovedFromParent) {
         this.setVisibility(android.view.View.INVISIBLE);
         if (parent != null)
            parent.removeView(this);
         mRemovedFromParent = true;
      }
   }

   public View GetView() {
      return this;
   }

   public void SetLParamWidth(int _w) {
      lparamW = _w;
   }

   public void SetLParamHeight(int _h) {
      lparamH = _h;
   }

   public void setLGravity(int _g) {
      lgravity = _g;
   }

   public void setLWeight(float _w) {
      lweight = _w;
   }

   public void SetLeftTopRightBottomWidthHeight(int _left, int _top, int _right, int _bottom, int _w, int _h) {
      marginLeft = _left;
      marginTop = _top;
      marginRight = _right;
      marginBottom = _bottom;
      lparamH = _h;
      lparamW = _w;
   }

   public void AddLParamsAnchorRule(int _rule) {
      lparamsAnchorRule[countAnchorRule] = _rule;
      countAnchorRule = countAnchorRule + 1;
   }

   public void AddLParamsParentRule(int _rule) {
      lparamsParentRule[countParentRule] = _rule;
      countParentRule = countParentRule + 1;
   }

   public void SetLayoutAll(int _idAnchor) {
      lparams.width  = lparamW;
      lparams.height = lparamH;
      lparams.setMargins(marginLeft,marginTop,marginRight,marginBottom);

      if (lparams instanceof RelativeLayout.LayoutParams) {
         if (_idAnchor > 0) {
            for (int i = 0; i < countAnchorRule; i++) {
               ((RelativeLayout.LayoutParams)lparams).addRule(lparamsAnchorRule[i], _idAnchor);
            }
         }
         for (int j = 0; j < countParentRule; j++) {
            ((RelativeLayout.LayoutParams)lparams).addRule(lparamsParentRule[j]);
         }
      }
      if (lparams instanceof FrameLayout.LayoutParams) {
         ((FrameLayout.LayoutParams)lparams).gravity = lgravity;
      }
      if (lparams instanceof LinearLayout.LayoutParams) {
         ((LinearLayout.LayoutParams)lparams).weight = lweight;
      }

      this.setLayoutParams(lparams);
   }

   public void ClearLayoutAll() {
      if (lparams instanceof RelativeLayout.LayoutParams) {
         for (int i = 0; i < countAnchorRule; i++) {
            ((RelativeLayout.LayoutParams)lparams).removeRule(lparamsAnchorRule[i]);
         }

         for (int j = 0; j < countParentRule; j++) {
            ((RelativeLayout.LayoutParams) lparams).removeRule(lparamsParentRule[j]);
         }
      }
      countAnchorRule = 0;
      countParentRule = 0;
   }

   public void SetId(int _id) { //wrapper method pattern ...
      this.setId(_id);
   }

   //write others [public] methods code here......
   //GUIDELINE: please, preferentially, init all yours params names with "_", ex: int _flag, String _hello ...
   public void SetTextOff(String _caption) {
      this.setTextOff(_caption);
   }

   public void SetTextOn(String _caption) {
      this.setTextOn(_caption);
   }

   public void SetChecked(boolean _state) {
      this.setChecked(_state);
   }

   public void Toggle() {
      this.toggle();
   }

   public boolean IsChecked(){
      return this.IsChecked();
   }
   
   /*
   private Drawable GetDrawableResourceById(int _resID) {
		return (Drawable)( this.controls.activity.getResources().getDrawable(_resID));	
   }
   */

   private int GetDrawableResourceId(String _resName) {   //    ../res/drawable
      try {
         Class<?> res = R.drawable.class;
         Field field = res.getField(_resName);  //"drawableName"
         int drawableId = field.getInt(null);
         return drawableId;
      }
      catch (Exception e) {
         Log.e("toglebutton", "Failure to get drawable id.", e);
         return 0;
      }
   }

   public void SetThumbIcon(String _thumbIconIdentifier) {	   //Api  16
      this.setThumbResource(GetDrawableResourceId(_thumbIconIdentifier));
   }

   public void DispatchOnToggleEvent(boolean _value) {
      enabled = _value;
   }
   
   /*
   public void SetShowText(boolean _state) {  //Api 21
	  this.setShowText(_state);
   }
   */

} //end class

