import React, { useState, useContext, useEffect } from 'react';
import { Container, Row, Col, Card, ButtonGroup, Button } from 'react-bootstrap';
import TodayLog from './home/TodayLog';
import Statistics from './home/Statistics';
import { useNavigate, useLocation } from 'react-router-dom';
import TokenContext from './TokenContext';
import { BsArrowLeft, BsArrowRight } from 'react-icons/bs'; // Example icons for date navigation

const HomePage = () => {
    const { getNutritionLogByDate, getFoodByDate, deleteLogById, updateLogById } = useContext(TokenContext);
    const location = useLocation();
    const [foodEntries, setFoodEntries] = useState([]);
    const [message, setMessage] = useState(null);
    const [todaysNutritionLog, setTodaysnutritionLog] = useState([]);
    const [logDate, setLogDate] = useState(new Date());

    const navigate = useNavigate();

    const NoFoodListAnime = () => {
        return (
            <Container className="empty-state-message text-center">
                <Row className="justify-content-center">
                    <Col md={8}>
                        <h1>Welcome!</h1>
                        <p>Start your day by adding some food to your log.</p>
                        <Button style={{backgroundColor: "#28a745;"}} variant="primary" onClick={navigateToAddFood}>Add Food</Button>
                    </Col>
                </Row>
            </Container>
        );
    };

    const isToday = (date) => {
        const today = new Date();
        return date.getDate() === today.getDate() &&
            date.getMonth() === today.getMonth() &&
            date.getFullYear() === today.getFullYear();
    };

    const formatDate = (date) => {
        const options = { year: 'numeric', month: 'long', day: 'numeric' };
        return date.toLocaleDateString(undefined, options);
    };

    useEffect(() => {
        if (location.state && location.state.message) {
            setMessage(location.state.message);
            setTimeout(() => {
                setMessage(null);
            }, 3000);
        }
    }, [location.state]);

    const fetchLogData = async () => {
        const response = await getNutritionLogByDate(logDate.toISOString().split('T')[0]);
        if (response !== null) {
            setTodaysnutritionLog(response.data);
        }
    };

    const fetchFoodLogData = async () => {
        const response = await getFoodByDate(logDate.toISOString().split('T')[0]);
        if (response !== null) {
            setFoodEntries(response.data);
        }
    };

    useEffect(() => {
        fetchLogData();
        fetchFoodLogData();
    }, [logDate]);

    const navigateToAddFood = () => {
        navigate('/search');
    };

    const deleteLog = async (intakeId) => {
        const response = await deleteLogById(intakeId);
        if (response !== null) {
            await fetchLogData();
            await fetchFoodLogData();
        }
    };

    const updateLog = async (intakeId, quantity, updateProportion) => {
        const response = await updateLogById(intakeId,quantity, updateProportion);
        if (response !== null) {
            await fetchLogData();
            await fetchFoodLogData();
        }
    };

    const handlePreviousDate = () => {
        const previousDate = new Date(logDate);
        previousDate.setDate(previousDate.getDate() - 1);
        setLogDate(previousDate);
    };

    const handleNextDate = () => {
        if (!isToday(logDate)) {
            const nextDate = new Date(logDate);
            nextDate.setDate(nextDate.getDate() + 1);
            setLogDate(nextDate);
        }
    };

    return (
        <Container>
            {message && <p className="alert alert-success alert-dismissible fade show">{message}</p>}
            <Row>
                <Card style={{marginBottom: '20px'}}>
                    <Card.Header style={{ backgroundColor: '#d5f5e3', color: '#006400', fontWeight: 'bold', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                        <ButtonGroup className="mr-2">
                            <Button variant="outline-secondary" onClick={handlePreviousDate}>
                                <BsArrowLeft />
                            </Button>
                        </ButtonGroup>
                        <div style={{ margin: '0 10px' }}>
                            {isToday(logDate) ? (
                                <span>Today</span>
                            ) : (
                                <span>{formatDate(logDate)}</span>
                            )}
                        </div>
                        <ButtonGroup className="ml-2">
                            <Button variant="outline-secondary" onClick={handleNextDate} disabled={isToday(logDate)}>
                                <BsArrowRight />
                            </Button>
                        </ButtonGroup>
                    </Card.Header>
                </Card>
            </Row>
            {foodEntries.length === 0 ? (
                <NoFoodListAnime />
            ) : (
                <>
                    <Row>
                        <TodayLog foodEntries={foodEntries} onAddFood={navigateToAddFood} onDeleteLog={deleteLog} onUpdateLog ={updateLog}/>
                    </Row>
                    <Row>
                        {todaysNutritionLog.length > 0 ? (
                            <Statistics nutrients={todaysNutritionLog} />
                        ) : (
                            <p>Loading...</p>
                        )}
                    </Row>
                </>
            )}
        </Container>
    );
};

export default HomePage;
