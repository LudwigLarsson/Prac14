package com.example.prac14;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {
    Button button;
    TextView counter;
    int realCounter = 0;
    public static final int FIRST = 101;
    public static final int SECOND = 102;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = getView().findViewById(R.id.button);
        counter = getView().findViewById(R.id.counter);
        registerForContextMenu(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().openContextMenu(button);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, FIRST, Menu.NONE, R.string.plus);
        menu.add(Menu.NONE, SECOND, Menu.NONE, R.string.minus);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case FIRST:
                realCounter++;
                counter.setText(realCounter + "");
                return true;
            case SECOND:
                realCounter--;
                counter.setText(realCounter + "");
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
