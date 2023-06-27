package com.example.computer_club.main;


import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.computer_club.EmptyListNotificator;
import com.example.computer_club.R;
import com.example.computer_club.databinding.FragmentMainBinding;
import com.example.computer_club.tables.Comp;
import com.example.computer_club.tables.User;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment{

    FragmentMainBinding binding;
    CompsAdapter adapter;
    MainViewModel viewModel;
    SharedPreferences sp;

    DialogBinder dialogBinder;
    DialogBinder delBinder;

    User user;
    public MainFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(getLayoutInflater());
        sp = requireContext().getSharedPreferences("APP_PREFS", MODE_PRIVATE);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        prepareBinders();
        prepareObservers();

        adapter = new CompsAdapter(dialogBinder);

        viewModel.getUserByEmail(sp.getString("EMAIL", " "));
        viewModel.getAllComps();

        binding.deleteCompBtn.setOnClickListener(v -> {
            adapter.setBinder(delBinder);
        });

        binding.addCompBtn.setOnClickListener(v -> {
            addComps();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            viewModel.getAllComps();
        });

        return binding.getRoot();
    }


    private void prepareBinders(){
        dialogBinder = (item) -> {
            CompDialog dialog = new CompDialog();
            Bundle bundle = new Bundle();
            bundle.putSerializable("COMP", item);
            bundle.putInt("USER_ID", user.id);
            dialog.setArguments(bundle);
            dialog.show(getChildFragmentManager(), "COMP_DIALOG");
        };

        delBinder = (item) -> {
            viewModel.deleteComp(item.id);
            adapter.removeItem(item);
        };
    }


    private void setContainerView(List<Comp> comps){
        if(comps.size() == 0){
            binding.container.removeAllViews();
            binding.container.addView(EmptyListNotificator.createEmptyListView(
                    binding.container,
                    R.drawable.ic_history,
                    R.string.history_empty
            ));
        } else{
            binding.container.removeAllViews();
            binding.container.addView(EmptyListNotificator.createRecyclerView(
                    requireContext(),
                    adapter,
                    new LinearLayoutManager(requireContext())
            ));
        }
    }


    private void prepareObservers(){
        viewModel.userData.observe(getViewLifecycleOwner(), user -> {
            this.user = user;

            if(user.isAdmin){
                binding.addCompBtn.setVisibility(View.VISIBLE);
                binding.deleteCompBtn.setVisibility(View.VISIBLE);
            }

        });

        viewModel.allCompsData.observe(getViewLifecycleOwner(), comps -> {
            adapter.refreshItems(comps);
            setContainerView(comps);
        });

        viewModel.deletedData.observe(getViewLifecycleOwner(), o -> adapter.setBinder(dialogBinder));
    }


    private void addComps(){
        List<Comp> list = new ArrayList<>();

        list.add(new Comp("ARDOR GAMING RAGE H290", "Intel Core i5-12400F, 6x2.5 ГГц, 16 ГБ DDR4, GeForce RTX 3060, SSD 512 ГБ", R.drawable.first));
        list.add(new Comp("ARDOR GAMING NEO M107", "Intel Core i3-12100F, 4x3.3 ГГц, 16 ГБ DDR4, GeForce GTX 1660 SUPER, SSD 512 ГБ", R.drawable.sec));
        list.add(new Comp("ZET GAMING WARD H051", "Intel Core i9-11900K, 8x3.5 ГГц, 32 ГБ DDR4, GeForce RTX 3080, SSD 1000 ГБ", R.drawable.third));
        list.add(new Comp("ARDOR GAMING EVO X019", "ntel Core i7-12700KF, 8x3.6 ГГц, 32 ГБ DDR5, GeForce RTX 4070 Ti, SSD 1000 ГБ", R.drawable.fourth));

        viewModel.addCompsList(list);
    }

}