package com.e.nytimes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NetworkErrorFragment.OnNetworkErrorFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NetworkErrorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NetworkErrorFragment extends Fragment {
    private OnNetworkErrorFragmentInteractionListener mListener;

    public NetworkErrorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NetworkErrorFragment.
     */
    public static NetworkErrorFragment newInstance() {
        NetworkErrorFragment fragment = new NetworkErrorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_network_error, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_network_error_try_again).setOnClickListener(v -> {
            onRetryPressed();
        });
    }

    public void onRetryPressed() {
        if (mListener != null) {
            mListener.onRetryClickedFromNetworkErrorPage();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNetworkErrorFragmentInteractionListener) {
            mListener = (OnNetworkErrorFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNetworkErrorFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnNetworkErrorFragmentInteractionListener {
        void onRetryClickedFromNetworkErrorPage();
    }
}
