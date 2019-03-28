import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import Modal from '@material-ui/core/Modal';
import Button from '@material-ui/core/Button';
import ResultPaper from './ResultPaper';
import Axios from 'axios';


function getModalStyle() {
  const top = 50;
  const left = 50;

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`,
  };
}

const styles = theme => ({
  paper: {
    position: 'absolute',
    width: theme.spacing.unit * 50,
    backgroundColor: theme.palette.background.paper,
    boxShadow: theme.shadows[5],
    padding: theme.spacing.unit * 4,
    outline: 'none',
  },
});

class History extends React.Component {
  state = {
    open: false,
    history: []
  };

  handleOpen = () => {
    this.setState({ open: true });
  };

  handleClose = () => {
    this.setState({ open: false });
  };

//   removeRow = (id)=>{

//         const url = `http://localhost:3000/api/results/${id}`;

//         Axios
//         .delete(url)
//         .then(

//         )
//         .catch(err => {
//           console.log(err);
//         });

//   }

  componentDidMount() {
    Axios
      .get("http://localhost:8080/api/results",{headers: {"Access-Control-Allow-Origin": "*"}})
      .then(response => {

        // create an array of contacts only with relevant data
        const rows = response.data.map(r => {
          return {
            id: r.id,
            query: r.query,
            result: r.result
          };
        });

        // create a new "State" object without mutating 
        // the original State object. 
        const newState = Object.assign({}, this.state, {
          history : rows
        });

        this.setState(newState);
      })
      .catch(error => console.log(error));
  }


  render() {
    const { classes } = this.props;

    return (
      <div>
        <Typography gutterBottom>Click to get see history</Typography>
        <Button onClick={this.handleOpen}>Open Modal</Button>
        <Modal
          aria-labelledby="simple-modal-title"
          aria-describedby="simple-modal-description"
          open={this.state.open}
          onClose={this.handleClose}
        >
          <div style={getModalStyle()} className={classes.paper}>
            <Typography variant="h6" id="modal-title">
              History
            </Typography>
            <ResultPaper row = {this.state.history[0]}/>
          </div>
        </Modal>
      </div>
    );
  }
}

History.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(History);