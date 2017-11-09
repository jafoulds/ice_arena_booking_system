var React = require('react');
var ReactDOM = require('react-dom');


class TestComponent extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (<h1>Hello there react world!</h1>);
    }

}

ReactDOM.render(<TestComponent />, document.getElementById('react'))

