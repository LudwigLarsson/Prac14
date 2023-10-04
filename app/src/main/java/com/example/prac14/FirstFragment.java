package com.example.prac14;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class FirstFragment extends Fragment {
    TextView text;
    public static final int FIRST = 101;
    public static final int SECOND = 102;
    public static final int THIRD = 103;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    //Добавляем метод onViewCreated - в отличие от onCreateView, он вызывается уже после создания пользовательского представления,
    //поэтому в нём можно использовать findViewById
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text = getView().findViewById(R.id.text); //во фрагментах перед findViewById нам нужно вызвать метод getView
        registerForContextMenu(text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().openContextMenu(text);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, FIRST, Menu.NONE, R.string.yellow);
        menu.add(Menu.NONE, SECOND, Menu.NONE, R.string.green);
        menu.add(Menu.NONE, THIRD, Menu.NONE, R.string.pink);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case FIRST:
                text.setTextColor(getResources().getColor(R.color.yellow));
                return true;
            case SECOND:
                text.setTextColor(getResources().getColor(R.color.green));
                return true;
            case THIRD:
                text.setTextColor(getResources().getColor(R.color.pink));
                return true;
        }
        return super.onContextItemSelected(item);
    }
}