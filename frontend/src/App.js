import React, { Component } from 'react';
import History from "./components/History";
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <h1>Hello</h1>
        <History/>
      </div>
    );
  }
}

export default App;
