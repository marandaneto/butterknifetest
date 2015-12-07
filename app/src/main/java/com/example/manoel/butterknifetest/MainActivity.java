package com.example.manoel.butterknifetest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity {

    // Annotate fields with @Bind and a view ID for Butter Knife to find and automatically cast
    // the corresponding view in your layout.
    @Bind(R.id.txvHelloWorld)
    TextView txvHelloWorld;

    // Bind pre-defined resources with @BindBool, @BindColor, @BindDimen, @BindDrawable, @BindInt,
    // @BindString, which binds an R.bool ID (or your specified type) to its corresponding field.
    @BindString(R.string.hello_world_butterknife)
    String strHelloWorld;

    @Bind(R.id.livNames)
    ListView livNames;

    // You can group multiple views into a List or array.
    @Bind({ R.id.txvFirstName, R.id.txvLastName })
    List<TextView> nameViews;

    @Bind(R.id.btnTest)
    Button btnTest;

    TextView txvFindById;

    // By default, both @Bind and listener bindings are required. An exception will be thrown if
    // the target view cannot be found.
    // To suppress this behavior and create an optional binding, add a @Nullable annotation to the
    // field or method.
    @Nullable
    @Bind(R.id.txvName)
    TextView mightNotBeThere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        txvHelloWorld.setText(strHelloWorld);

        nameViews.get(0).setText("Manoel");
        nameViews.get(1).setText("Neto");

        List<String> namesList = new ArrayList<>();

        for(int i = 1; i <= 10; i++) {
            namesList.add("Name " + i);
        }

        livNames.setAdapter(new NamesAdapter(this, namesList));

        // An Android Property can also be used with the apply method.
        ButterKnife.apply(nameViews, View.ALPHA, 0.5f);

        // Also included are findById methods which simplify code that still has to find views on a
        // View, Activity, or Dialog. It uses generics to infer the return type and automatically
        // performs the cast.
        txvFindById = ButterKnife.findById(this, R.id.txvFindById);
        txvFindById.setText("findById");
    }

    // Listeners can also automatically be configured onto methods.
    // All arguments to the listener method are optional.
    @OnClick(R.id.btnTest)
    public void btnTestClick() {
        btnTest.setText("Clicked!");

        // The apply method allows you to act on all the views in a list at once.
        ButterKnife.apply(nameViews, ENABLED, false);

//        Define a specific type and it will automatically be cast.
//        @OnClick(R.id.submit)
//        public void sayHi(Button button) {
//            button.setText("Hello!");
//        }

//        Specify multiple IDs in a single binding for common event handling.
//        @OnClick({ R.id.door1, R.id.door2, R.id.door3 })
//        public void pickDoor(DoorView door) {
//            if (door.hasPrizeBehind()) {
//                Toast.makeText(this, "You win!", LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "Try again", LENGTH_SHORT).show();
//            }
//        }

//        Custom views can bind to their own listeners by not specifying an ID.
//        public class FancyButton extends Button {
//            @OnClick
//            public void onClick() {
//                // TODO do something!
//            }
//        }
    }

    // Method annotations whose corresponding listener has multiple callbacks can be used to bind
    // to any one of them. Each annotation has a default callback that it binds to. Specify an
    // alternate using the callback parameter.
    @OnItemSelected(R.id.livNames)
    void livNamesItemSelected(int position) {
        Toast.makeText(this, "Selected: " +
                livNames.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();
    }

    @OnItemClick(R.id.livNames)
    public void livNamesItemClicked(int position) {
        Toast.makeText(this, "Clicked: " +
                livNames.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();
    }

    // Action and Setter interfaces allow specifying simple behavior.
    static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override public void set(View view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };
}
