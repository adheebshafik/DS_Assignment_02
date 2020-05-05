import React, { Component } from 'react'
import axios from 'axios'
import {Table} from 'reactstrap'

class App extends Component {
  
  state = {
      sensors: [],
      
  }

  componentDidMount() {
    axios.get("http://localhost:3000/sensors")
      .then(response => {
        this.setState({
          sensors: response.data
        })
      })
       
      this.interval = setInterval(() => this.setState({ time: Date.now() }), 40000);
     
  }

  componentWillUnmount() {
    clearInterval(this.interval);
  }


  render() {

    let sensors = this.state.sensors.map(sensor => {
    let smokeNumber = Math.floor((Math.random() * 10) + 1)
    let co2Number = Math.floor((Math.random() * 10) + 1)

      return (
        <tr key={sensor.id}>
          <td>{sensor.id}</td>
          <td>{sensor.sensorStatus}</td>
          <td>{sensor.floorNo}</td>
          <td>{sensor.roomNo}</td>
          <td className={smokeNumber > 5 ? 'bg-danger' : ''}> {smokeNumber}  </td>
          <td className={co2Number > 5 ? 'bg-danger': ''}> {co2Number}  </td>
        </tr>
      )
    })

    return (
      <div className="App container">
      <div className="text-center">
      <h2 className="mt-4">Fire Alarm Monitoring System</h2>
      </div>
        <Table className="mt-4 center-block">

          <thead>
            <tr>
              <th>#</th>
              <th>Sensor Status</th>
              <th>Floor Number</th>
              <th>Room number</th>
              <th>Smoke Level</th>
              <th>CO2 Level</th>
            </tr>
          </thead>

        <tbody>{sensors}</tbody>

        </Table>

      </div>
    )
  }
}

export default App

