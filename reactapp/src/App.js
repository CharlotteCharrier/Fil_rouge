import "./App.css";

import Feed from "./Feed";
import Home from "./Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" Component={Home} />
        <Route path="/feed" Component={Feed} />
      </Routes>
    </Router>
  );
}

export default App;
