import "./App.css";

function App() {
  return (
    <div className="App">
      <form action="/register/save" method="post">
        <div>
          <label for="email">Adresse mail</label>
          <input type="email" name="email" id="email" placeholder="Email" />
        </div>

        <div>
          <label for="password">Mot de passe</label>
          <input type="password" name="password" id="password" placeholder="Password" />
        </div>

        <input type="submit" value="Register" id="registerBtn" />
      </form>
    </div>
  );
}

export default App;
