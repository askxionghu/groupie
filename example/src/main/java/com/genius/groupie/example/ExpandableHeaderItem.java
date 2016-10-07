package com.genius.groupie.example;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.annotation.StringRes;
import android.view.View;

import com.genius.groupie.ExpandableGroup;
import com.genius.groupie.ExpandableItem;
import com.genius.groupie.example.databinding.ItemHeaderBinding;
import com.genius.groupie.example.item.HeaderItem;

public class ExpandableHeaderItem extends HeaderItem implements ExpandableItem {

    private ExpandableGroup expandableGroup;

    public ExpandableHeaderItem(@StringRes int titleStringResId, @StringRes int subtitleResId) {
        super(titleStringResId, subtitleResId);
    }

    @Override public void bind(final ItemHeaderBinding viewBinding, int position) {
        super.bind(viewBinding, position);
        viewBinding.icon.setVisibility(View.VISIBLE);
        viewBinding.icon.setImageResource(expandableGroup.isExpanded() ? R.drawable.collapse : R.drawable.expand);
        viewBinding.icon.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                expandableGroup.onToggleExpanded();
                bindIcon(viewBinding);
            }
        });
    }

    private void bindIcon(ItemHeaderBinding viewBinding) {
        viewBinding.icon.setVisibility(View.VISIBLE);
        viewBinding.icon.setImageResource(expandableGroup.isExpanded() ? R.drawable.collapse_animated : R.drawable.expand_animated);
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) viewBinding.icon.getDrawable();
        drawable.start();
    }

    @Override public void setExpandableGroup(ExpandableGroup onToggleListener) {
        this.expandableGroup = onToggleListener;
    }
}
