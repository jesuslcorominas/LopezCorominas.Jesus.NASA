package com.jesuslcorominas.nasa.app.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jesuslcorominas.nasa.app.view.adapter.item.ItemView;
import com.jesuslcorominas.nasa.app.view.control.ViewWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Clase base para loa adaptadores de la aplicacion.
 * <p>
 * La clase esta parametrizada con el VO que se va mostrar en pantalla y la vista que lo va a mostrar
 * que debera implementar {@link ItemView}.
 *
 * @author Jesus Lopez Corominas
 * @see <a href="https://github.com/excilys/androidannotations/wiki/Adapters-and-lists">Adaptadores y listas</a>
 */
public abstract class RecyclerViewBaseAdapter<T, V extends View & ItemView<T>> extends RecyclerView.Adapter<ViewWrapper<V>> {

    private static final String TAG = RecyclerViewBaseAdapter.class.getName();

    protected List<T> items = new ArrayList<>();

    private OnItemClickListener<T, V> itemClickListener;
    private OnItemLongClickListener<T, V> itemLongClickListener;

    private boolean multiSelect = false;
    private ArrayList<Integer> selectedItems = new ArrayList<Integer>();

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(ViewWrapper<V> viewHolder, final int position) {
        final V view = viewHolder.getView();
        final T data = items.get(position);

        view.bind(data, position);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(position, view, data);
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return onLongItemClick(position, view, data);
            }
        });
    }

    @Override
    public final ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<V>(onCreateItemView(parent, viewType));
    }

    public final void addItems(Collection<T> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public final void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public final void update(T modifiedItem) {
        int index = items.indexOf(modifiedItem);
        if (index != -1) {
            items.set(index, modifiedItem);
        } else {
            Log.w(TAG, "No se ha podido actualizar el listado porque el elemento no existia");
        }

        notifyDataSetChanged();
    }

    public final void delete(T deletedItem) {
        int index = items.indexOf(deletedItem);
        if (index != -1) {
            items.remove(index);
        }
        notifyDataSetChanged();
    }

    public List<T> getItems() {
        return items;
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

    // ==============================
    // OnItemClickListener
    // ==============================
    private void onItemClick(int position, V view, T data) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(position, view, data);
        }
    }

    private boolean onLongItemClick(int position, V view, T data) {
        return itemLongClickListener != null && itemLongClickListener.onLongItemClick(position, view, data);
    }

    public void setOnItemClickListener(OnItemClickListener<T, V> listener) {
        this.itemClickListener = listener;
    }

    public void setOnLongItemClickListener(OnItemLongClickListener<T, V> listener) {
        this.itemLongClickListener = listener;
    }

    /**
     * Interface para gestionar el clic en un item de la lista
     *
     * @param <T> El elemento pintado en el item
     * @param <V> El item pulsado
     */
    public interface OnItemClickListener<T, V> {
        /**
         * @param position
         * @param view
         * @param data
         */
        void onItemClick(int position, V view, T data);
    }

    /**
     * Interface para gestionar la pulsacion prolongada en el item de la lista
     *
     * @param <T> El elemento pintado en el item
     * @param <V> El item pulsado
     */
    public interface OnItemLongClickListener<T, V> {

        /**
         * Click prolongado en un elemento de la lista
         *
         * @param position La posicion en la lista del item pulsado
         * @param view     La vista pulsada
         * @param data     El elemento pintado en el item
         * @return True si se gestiona la pulsacion prolongada. False si hay que propagar el evento.
         */
        boolean onLongItemClick(int position, V view, T data);
    }


}
