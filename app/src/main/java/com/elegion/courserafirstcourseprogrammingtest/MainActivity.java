package com.elegion.courserafirstcourseprogrammingtest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements CreateCharacterFragment.Callback {

    public static final String CREATOR = "Creator";
    private CharacterCreator mCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            mCreator = new CharacterCreator();
            setFragment(CreateCharacterFragment.newInstance(), false);
        } else {
            mCreator = (CharacterCreator) savedInstanceState.getSerializable(CREATOR);
        }

    }

    private void setFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateCharacterStarted() {
        CreateCharacterFragment fragment = (CreateCharacterFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        fragment.configureCreator(mCreator);
    }

    @Override
    public void onCreateCharacterCompleted(Character character) {
        setFragment(CharacterFragment.newInstance(character), true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CREATOR, mCreator);
    }
}
