package de.efischer.financetracker.accounts.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.efischer.financetracker.R;

/**
 * Fragment that represents a list of all accounts.
 * @author Evelyn Fischer
 */

public class AccountOverviewFragment extends Fragment {

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 1;
    private static final int DATASET_COUNT = 60;
    private String[] dataset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_overview, container, false);
        view.setTag(TAG);
        // BEGIN_INCLUDE(initializeRecyclerView)
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        AccountListAdapter adapter = new AccountListAdapter(dataset);
        // Set CustomAdapter as the adapter for RecyclerView.
        recyclerView.setAdapter(adapter);

        return view;
    }

        /**
         * Generates Strings for RecyclerView's adapter. This data would usually come
         * from a local content provider or remote server.
         */
        private void initDataset() {
            dataset = new String[DATASET_COUNT];
            for (int i = 0; i < DATASET_COUNT; i++) {
                dataset[i] = "This is element #" + i;
            }
        }
    }

