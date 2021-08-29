package com.example.secondapp; //Информация о пакете, в котором находится приложение
//импорт необходимых библиотек

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity { //класс, представляющий главную активность, основное окно программы
    RecyclerView recyclerView; //инициализируем переменную типа RecyclerView - представление прокручивающегося длинного спсика
    UserAdapter userAdapter; //инициализируем объект типа UserAdapter - адаптер при помощи которого можно отобразить элементы списка
    Button add;
    ArrayList<User> userList = new ArrayList<>();

    @Override //переопределение метода onCreate
    protected void onCreate(Bundle savedInstanceState) { //метод создающий основную активность
        super.onCreate(savedInstanceState); //конструктор из переопределяемого метода
        setContentView(R.layout.activity_main); //установка содержимого вьюшки
        setTitle("Список пользователей");

        add = findViewById(R.id.add);
        add.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
            intent.putExtra("msg", "add");
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.recyclerview); //связываем виджет recyclerView на экране с переменной в коде
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this)); //установливаем в качестве макета линейную компоновку и в нее передаем активность в которой находимся
    }

    private  void recyclerViewInit(){
        Users users = new Users();
        userList = users.getUserLists();
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewInit();
    }

    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{ //метод, отдающий каждый отдельный элемент в списке
        final TextView itemTextView; //Инициализируем отдельный элемент в списке
        private User mUser; //Инициализируем переменную типа User

        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) { //Конструктор
            super(inflater.inflate(R.layout.single_item, viewGroup, false)); //реализация методов родителя. Передаем макет single_item.xml, который будем раздувать
            itemTextView = itemView.findViewById(R.id.itemTextView); //связываем отображение строки на экране с переменной в коде
            itemView.setOnClickListener(this); //Включаем прослушиватель
        }
        public void bind(User user){ //Метод для вывода элемента списка
            mUser = user; //Присваиваем переменной mUser объект типа User, который в данный момент выводится
            itemTextView.setText(user.getName() + " " + user.getLastName()); //Вывод элемента списка
        }
        @Override
        public void onClick(View view){ //Метод для обработки клика
            Intent intent = new Intent(MainActivity.this, UserActivity.class); //Создаем Intent для новой активности
            intent.putExtra("selectItem", mUser); //Передаем через него данные о текущем пользователе
            startActivity(intent); //Стартуем новую активность
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder> { //Метод, отображающий элементы списка
        final ArrayList<User> users; //Инициализируем список пользователей
        public UserAdapter(ArrayList<User> users) { //Конструктор класса
            this.users = users; //записываем в текущий список пользователей, переданный в метод, созданный выше список.
        }

        @Override //переопределение метода интерфейса родителя
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) { //Метод создает и инициализирует представление ViewHolder и связанные с ним View
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this); //создание объекта типа LayoutInflater для наполнения элемента списка
            return new UserHolder(inflater, parent);
        }

        @Override //переопределение метода интерфейса родителя
        public void onBindViewHolder(UserHolder userHolder, int position) {//Метод извлекает данные и использует их для заполнения макета
            User user = users.get(position); //Инициализируем переменную user, в которую записываем элемент списка users в соответствии с position
//            String userString = user.getUserName() + "\n" + user.getUserLastName();
            userHolder.bind(user); //Вызов метода bind для вывода одной позиции списка
        }

        @Override //переопределение метода интерфейса родителя
        public int getItemCount() { //Метод определяющий сколько элементов списка будет на экране
            return users.size(); //Возвращаем количество элементов, определенное из размера списка users
        }
    }
}
