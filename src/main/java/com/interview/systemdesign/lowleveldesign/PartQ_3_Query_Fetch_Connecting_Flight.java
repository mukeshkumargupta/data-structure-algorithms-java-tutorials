package com.interview.systemdesign.lowleveldesign;

public class PartQ_3_Query_Fetch_Connecting_Flight {
    /*
    Fetching connecting flights from a database involves querying for all possible flight combinations where there is an intermediary stop between the source and destination. Here's a detailed explanation of how you can achieve this:

        1. Key Concepts
        Direct Flight: A single flight from the source to the destination.
        Connecting Flight: A combination of two or more flights, where:
        The destination of the first flight matches the source of the second flight.
        The arrival time of the first flight is earlier than the departure time of the second flight, with a minimum layover time considered.
        2. Steps to Fetch Connecting Flights
        Schema Example
        Assume the flight table is structured as follows:

        Column	Description
        flight_id	Unique ID for the flight
        source	Source airport code or name
        destination	Destination airport code or name
        departure_time	Departure time of the flight
        arrival_time	Arrival time of the flight
        price	Price of the flight
        airline	Airline operating the flight
        Query Logic
        Find All Direct Flights: First, check if there are direct flights from the source to the destination.

        Find Connecting Flights:

        Select flights where the source matches the requested source.
        Join these with flights where the destination matches the requested destination.
        Ensure that the arrival time of the first flight is less than the departure time of the second flight with a minimum layover time (e.g., 1 hour).
        Union the Results: Combine direct and connecting flights into a single result set.


        Fetching connecting flights from a database involves querying for all possible flight combinations where there is an intermediary stop between the source and destination. Here's a detailed explanation of how you can achieve this:

        1. Key Concepts
        Direct Flight: A single flight from the source to the destination.
        Connecting Flight: A combination of two or more flights, where:
        The destination of the first flight matches the source of the second flight.
        The arrival time of the first flight is earlier than the departure time of the second flight, with a minimum layover time considered.
        2. Steps to Fetch Connecting Flights
        Schema Example
        Assume the flight table is structured as follows:

        Column	Description
        flight_id	Unique ID for the flight
        source	Source airport code or name
        destination	Destination airport code or name
        departure_time	Departure time of the flight
        arrival_time	Arrival time of the flight
        price	Price of the flight
        airline	Airline operating the flight
        Query Logic
        Find All Direct Flights: First, check if there are direct flights from the source to the destination.

        Find Connecting Flights:

        Select flights where the source matches the requested source.
        Join these with flights where the destination matches the requested destination.
        Ensure that the arrival time of the first flight is less than the departure time of the second flight with a minimum layover time (e.g., 1 hour).
        Union the Results: Combine direct and connecting flights into a single result set.

        3. SQL Query for Connecting Flights

        WITH DirectFlights AS (
            SELECT
                flight_id AS direct_flight_id,
                source,
                destination,
                departure_time,
                arrival_time,
                price AS total_price
            FROM
                flights
            WHERE
                source = 'New York' AND destination = 'London'
        ),
        ConnectingFlights AS (
            SELECT
                f1.flight_id AS first_leg_id,
                f1.source AS source,
                f1.destination AS layover,
                f1.departure_time AS first_departure_time,
                f1.arrival_time AS first_arrival_time,
                f2.flight_id AS second_leg_id,
                f2.destination AS destination,
                f2.departure_time AS second_departure_time,
                f2.arrival_time AS second_arrival_time,
                (f1.price + f2.price) AS total_price
            FROM
                flights f1
            INNER JOIN
                flights f2
            ON
                f1.destination = f2.source
                AND f1.arrival_time + INTERVAL '1 hour' <= f2.departure_time
            WHERE
                f1.source = 'New York' AND f2.destination = 'London'
        )
        SELECT * FROM DirectFlights
        UNION ALL
        SELECT * FROM ConnectingFlights;

        4. Query Breakdown
        DirectFlights CTE:

        Fetches all flights directly connecting the source and destination.
        ConnectingFlights CTE:

        Finds pairs of flights (f1 and f2) where:
        The destination of f1 matches the source of f2 (connecting point).
        The arrival time of f1 is earlier than the departure time of f2, with at least 1-hour layover.
        Final Query:

        Combines the results of DirectFlights and ConnectingFlights using a UNION ALL.
        5. Handling Edge Cases
        No Direct or Connecting Flights:

        The result set will be empty. Ensure your API or UI gracefully handles this case by displaying a "No flights available" message.
        Multiple Connections:

        For flights requiring more than one connection (e.g., source → stop1 → stop2 → destination), you can extend the query by joining more instances of the flights table.
        6. Example Data
        flight_id	source	destination	departure_time	arrival_time	price	airline
        1	New York	Dubai	2025-02-10 09:00:00	2025-02-10 18:00:00	500	Emirates
        2	Dubai	London	2025-02-10 20:00:00	2025-02-10 23:00:00	300	Emirates
        3	New York	London	2025-02-10 10:00:00	2025-02-10 22:00:00	800	BritishAir
        7. Example Query Execution
        Using the example data:

        Direct Flight:

        Flight 3 (New York → London) is fetched from DirectFlights.
        Connecting Flight:

        Flight 1 (New York → Dubai) connects with Flight 2 (Dubai → London).
        Total Price: 500 + 300 = 800.
        Result:

        direct_flight_id	source	destination	departure_time	arrival_time	total_price
        3	New York	London	2025-02-10 10:00:00	2025-02-10 22:00:00	800
        first_leg_id	source	layover	first_departure_time	first_arrival_time	second_leg_id	destination	second_departure_time	second_arrival_time	total_price
        1	New York	Dubai	2025-02-10 09:00:00	2025-02-10 18:00:00	2	London	2025-02-10 20:00:00	2025-02-10 23:00:00	800
        8. Enhancements
        Dynamic Parameters:

        Replace hardcoded values ('New York', 'London') with dynamic query parameters from your API.
        Performance:

        Add indexes on source, destination, and departure_time columns.
        Use caching (e.g., Redis) to store frequently accessed flight data.
        Real-Time Updates:

        Regularly update prices and availability using airline APIs to ensure the data is up-to-date.
        Would you like help implementing this logic in your code?







        You said:
        Pls suggest more than one  connecting flight then how query will look like
        ChatGPT said:
        ChatGPT
        If you want to support multiple connecting flights (e.g., source → stop1 → stop2 → destination), you will need to extend the query logic to handle additional levels of connections. Here's how you can achieve this:

        Query Logic for Multiple Connecting Flights
        To support up to two connecting flights (3 legs in total):

        Start by finding flights for the first leg (from source to stop1).
        Join these with flights for the second leg (from stop1 to stop2).
        Finally, join these with flights for the third leg (from stop2 to destination).
        Ensure layover conditions are met between consecutive flights.
        SQL Query for Up to Two Connecting Flights
        sql
        Copy
        Edit
        WITH
        DirectFlights AS (
            SELECT
                flight_id AS direct_flight_id,
                source,
                destination,
                departure_time,
                arrival_time,
                price AS total_price
            FROM
                flights
            WHERE
                source = 'New York' AND destination = 'London'
        ),
        OneStopFlights AS (
            SELECT
                f1.flight_id AS first_leg_id,
                f1.source AS source,
                f1.destination AS layover,
                f1.departure_time AS first_departure_time,
                f1.arrival_time AS first_arrival_time,
                f2.flight_id AS second_leg_id,
                f2.destination AS destination,
                f2.departure_time AS second_departure_time,
                f2.arrival_time AS second_arrival_time,
                (f1.price + f2.price) AS total_price
            FROM
                flights f1
            INNER JOIN
                flights f2
            ON
                f1.destination = f2.source
                AND f1.arrival_time + INTERVAL '1 hour' <= f2.departure_time
            WHERE
                f1.source = 'New York' AND f2.destination = 'London'
        ),
        TwoStopFlights AS (
            SELECT
                f1.flight_id AS first_leg_id,
                f1.source AS source,
                f1.destination AS first_layover,
                f1.departure_time AS first_departure_time,
                f1.arrival_time AS first_arrival_time,
                f2.flight_id AS second_leg_id,
                f2.destination AS second_layover,
                f2.departure_time AS second_departure_time,
                f2.arrival_time AS second_arrival_time,
                f3.flight_id AS third_leg_id,
                f3.destination AS destination,
                f3.departure_time AS third_departure_time,
                f3.arrival_time AS third_arrival_time,
                (f1.price + f2.price + f3.price) AS total_price
            FROM
                flights f1
            INNER JOIN
                flights f2
            ON
                f1.destination = f2.source
                AND f1.arrival_time + INTERVAL '1 hour' <= f2.departure_time
            INNER JOIN
                flights f3
            ON
                f2.destination = f3.source
                AND f2.arrival_time + INTERVAL '1 hour' <= f3.departure_time
            WHERE
                f1.source = 'New York' AND f3.destination = 'London'
        )
        SELECT * FROM DirectFlights
        UNION ALL
        SELECT * FROM OneStopFlights
        UNION ALL
        SELECT * FROM TwoStopFlights
        ORDER BY total_price, first_departure_time;
        Query Explanation
        DirectFlights CTE:

        Fetches all flights that directly connect the source and destination.
        OneStopFlights CTE:

        Finds pairs of flights where:
        The destination of the first flight (f1) matches the source of the second flight (f2).
        The layover time between f1 and f2 is at least 1 hour.
        The source of f1 matches the search source, and the destination of f2 matches the search destination.
        TwoStopFlights CTE:

        Adds a third flight (f3) to the chain, where:
        The destination of the second flight (f2) matches the source of the third flight (f3).
        The layover time between f2 and f3 is at least 1 hour.
        The source of f1 matches the search source, and the destination of f3 matches the search destination.
        Final Query:

        Combines all results (DirectFlights, OneStopFlights, TwoStopFlights) using a UNION ALL.
        Orders the results by total_price and first_departure_time to prioritize cheaper and earlier flights.
        Handling n-Stop Flights
        For more than two stops (e.g., source → stop1 → stop2 → stop3 → destination), you can extend the query further by:

        Adding additional joins for the flights table.
        Incrementally chaining conditions for layover times and matching sources/destinations.
        Example Data
        flight_id	source	destination	departure_time	arrival_time	price	airline
        1	New York	Dubai	2025-02-10 09:00:00	2025-02-10 18:00:00	500	Emirates
        2	Dubai	Frankfurt	2025-02-10 20:00:00	2025-02-11 01:00:00	300	Lufthansa
        3	Frankfurt	London	2025-02-11 04:00:00	2025-02-11 06:00:00	200	Lufthansa
        4	New York	London	2025-02-10 10:00:00	2025-02-10 22:00:00	800	BritishAir
        Example Results
        Type	Flight Details	Total Price
        Direct Flight	New York → London (Flight 4)	800
        One-Stop Flight	New York → Dubai (Flight 1), Dubai → London (Flight 2)	800
        Two-Stop Flight	New York → Dubai (Flight 1), Dubai → Frankfurt (Flight 2), Frankfurt → London (Flight 3)	1000
        Performance Optimizations
        Indexing:

        Create indexes on source, destination, and departure_time to speed up queries.
        Pagination:

        If the result set is large, use LIMIT and OFFSET to fetch results in pages.
        Precompute Routes:

        Precompute common connecting routes and store them in a separate table to reduce query complexity.

     */
}
