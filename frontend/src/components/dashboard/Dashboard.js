import React, { Component } from 'react'
import Header from '../header/Header'
import Input from '../input-box/Input';

export class Dashboard extends Component {
  render() {
    return (
      <div>
        <Header />
        <Input />
      </div>
    )
  }
}

export default Dashboard
