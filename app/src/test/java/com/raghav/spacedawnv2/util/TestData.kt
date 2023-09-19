package com.raghav.spacedawnv2.util

const val launchesResponseDtoString = """{
    "count": 157,
    "next": "https://lldev.thespacedevs.com/2.2.0/launch/upcoming/?limit=5&offset=5",
    "previous": null,
    "results": [
    {
        "id": "7c126e4f-4afd-4c25-bf6f-9017666b56ee",
        "url": "https://lldev.thespacedevs.com/2.2.0/launch/7c126e4f-4afd-4c25-bf6f-9017666b56ee/",
        "slug": "antares-230-cygnus-crs-2-ng-19",
        "name": "Antares 230+ | Cygnus CRS-2 NG-19",
        "status": {
        "id": 1,
        "name": "Go for Launch",
        "abbrev": "Go",
        "description": "Current T-0 confirmed by official or reliable sources."
    },
        "last_updated": "2023-07-29T20:54:17Z",
        "net": "2023-08-02T00:31:11Z",
        "window_end": "2023-08-02T00:31:11Z",
        "window_start": "2023-08-02T00:31:11Z",
        "net_precision": {
        "id": 0,
        "name": "Second",
        "abbrev": "SEC",
        "description": "The T-0 is accurate to the second."
    },
        "probability": 80,
        "weather_concerns": "Cumulus Clouds",
        "holdreason": "",
        "failreason": "",
        "hashtag": null,
        "launch_service_provider": {
        "id": 257,
        "url": "https://lldev.thespacedevs.com/2.2.0/agencies/257/",
        "name": "Northrop Grumman Space Systems",
        "type": "Commercial"
    },
        "rocket": {
        "id": 2685,
        "configuration": {
        "id": 210,
        "url": "https://lldev.thespacedevs.com/2.2.0/config/launcher/210/",
        "name": "Antares 230+",
        "family": "Antares",
        "full_name": "Antares 230+",
        "variant": "230+"
    }
    },
        "mission": {
        "id": 6004,
        "name": "Cygnus CRS-2 NG-19",
        "description": "This is the 19th flight of the Orbital ATK's uncrewed resupply spacecraft Cygnus and its 18th flight to the International Space Station under the Commercial Resupply Services contract with NASA.",
        "launch_designator": null,
        "type": "Resupply",
        "orbit": {
        "id": 8,
        "name": "Low Earth Orbit",
        "abbrev": "LEO"
    }
    },
        "pad": {
        "id": 76,
        "url": "https://lldev.thespacedevs.com/2.2.0/pad/76/",
        "agency_id": 44,
        "name": "Launch Area 0 A",
        "info_url": null,
        "wiki_url": "",
        "map_url": "https://www.google.com/maps?q=37.8337,-75.4881",
        "latitude": "37.8337",
        "longitude": "-75.4881",
        "location": {
        "id": 21,
        "url": "https://lldev.thespacedevs.com/2.2.0/location/21/",
        "name": "Wallops Island, Virginia, USA",
        "country_code": "USA",
        "map_image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launch_images/location_21_20200803142423.jpg",
        "timezone_name": "America/New_York",
        "total_launch_count": 76,
        "total_landing_count": 0
    },
        "country_code": "USA",
        "map_image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launch_images/pad_76_20200803143538.jpg",
        "total_launch_count": 18,
        "orbital_launch_attempt_count": 18
    },
        "webcast_live": false,
        "image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launcher_images/antares2520230252b_image_20191102024633.jpeg",
        "infographic": null,
        "program": [
        {
            "id": 11,
            "url": "https://lldev.thespacedevs.com/2.2.0/program/11/",
            "name": "Commercial Resupply Services",
            "description": "Commercial Resupply Services (CRS) are a series of flights awarded by NASA for the delivery of cargo and supplies to the International Space Station.The first CRS contracts were signed in 2008 and awarded $1.6 billion to SpaceX for twelve cargo Dragon and $1.9 billion to Orbital Sciences for eight Cygnus flights, covering deliveries to 2016. The Falcon 9 and Antares rockets were also developed under the CRS program to deliver cargo spacecraft to the ISS.",
            "agencies": [
            {
                "id": 44,
                "url": "https://lldev.thespacedevs.com/2.2.0/agencies/44/",
                "name": "National Aeronautics and Space Administration",
                "type": "Government"
            },
            {
                "id": 257,
                "url": "https://lldev.thespacedevs.com/2.2.0/agencies/257/",
                "name": "Northrop Grumman Space Systems",
                "type": "Commercial"
            },
            {
                "id": 1020,
                "url": "https://lldev.thespacedevs.com/2.2.0/agencies/1020/",
                "name": "Sierra Nevada Corporation",
                "type": "Commercial"
            },
            {
                "id": 121,
                "url": "https://lldev.thespacedevs.com/2.2.0/agencies/121/",
                "name": "SpaceX",
                "type": "Commercial"
            }
            ],
            "image_url": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/program_images/commercial2520_program_20201129212219.png",
            "start_date": "2008-12-23T00:00:00Z",
            "end_date": null,
            "info_url": null,
            "wiki_url": "https://en.wikipedia.org/wiki/Commercial_Resupply_Services#Commercial_Resupply_Services",
            "mission_patches": []
        },
        {
            "id": 17,
            "url": "https://lldev.thespacedevs.com/2.2.0/program/17/",
            "name": "International Space Station",
            "description": "The International Space Station programme is tied together by a complex set of legal, political and financial agreements between the sixteen nations involved in the project, governing ownership of the various components, rights to crewing and utilization, and responsibilities for crew rotation and resupply of the International Space Station. It was conceived in 1984 by President Ronald Reagan, during the Space Station Freedom project as it was originally called.",
            "agencies": [
            {
                "id": 16,
                "url": "https://lldev.thespacedevs.com/2.2.0/agencies/16/",
                "name": "Canadian Space Agency",
                "type": "Government"
            },
            {
                "id": 27,
                "url": "https://lldev.thespacedevs.com/2.2.0/agencies/27/",
                "name": "European Space Agency",
                "type": "Multinational"
            },
            {
                "id": 37,
                "url": "https://lldev.thespacedevs.com/2.2.0/agencies/37/",
                "name": "Japan Aerospace Exploration Agency",
                "type": "Government"
            },
            {
                "id": 44,
                "url": "https://lldev.thespacedevs.com/2.2.0/agencies/44/",
                "name": "National Aeronautics and Space Administration",
                "type": "Government"
            },
            {
                "id": 63,
                "url": "https://lldev.thespacedevs.com/2.2.0/agencies/63/",
                "name": "Russian Federal Space Agency (ROSCOSMOS)",
                "type": "Government"
            }
            ],
            "image_url": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/program_images/international2_program_20201129184745.png",
            "start_date": "1998-11-20T06:40:00Z",
            "end_date": null,
            "info_url": "https://www.nasa.gov/mission_pages/station/main/index.html",
            "wiki_url": "https://en.wikipedia.org/wiki/International_Space_Station_programme",
            "mission_patches": []
        }
        ],
        "orbital_launch_attempt_count": 6474,
        "location_launch_attempt_count": 77,
        "pad_launch_attempt_count": 19,
        "agency_launch_attempt_count": 14,
        "orbital_launch_attempt_count_year": 116,
        "location_launch_attempt_count_year": 4,
        "pad_launch_attempt_count_year": 1,
        "agency_launch_attempt_count_year": 1
    },
    {
        "id": "5749ccdb-a743-49e9-b7d0-3c018dc68ae3",
        "url": "https://lldev.thespacedevs.com/2.2.0/launch/5749ccdb-a743-49e9-b7d0-3c018dc68ae3/",
        "slug": "long-march-4c-fengyun-3f",
        "name": "Long March 4C | Fengyun-3F",
        "status": {
        "id": 1,
        "name": "Go for Launch",
        "abbrev": "Go",
        "description": "Current T-0 confirmed by official or reliable sources."
    },
        "last_updated": "2023-07-29T10:49:27Z",
        "net": "2023-08-03T03:55:00Z",
        "window_end": "2023-08-03T04:12:00Z",
        "window_start": "2023-08-03T03:41:00Z",
        "net_precision": {
        "id": 1,
        "name": "Minute",
        "abbrev": "MIN",
        "description": "The T-0 is accurate to the minute."
    },
        "probability": null,
        "weather_concerns": null,
        "holdreason": "",
        "failreason": "",
        "hashtag": null,
        "launch_service_provider": {
        "id": 88,
        "url": "https://lldev.thespacedevs.com/2.2.0/agencies/88/",
        "name": "China Aerospace Science and Technology Corporation",
        "type": "Government"
    },
        "rocket": {
        "id": 7971,
        "configuration": {
        "id": 64,
        "url": "https://lldev.thespacedevs.com/2.2.0/config/launcher/64/",
        "name": "Long March 4C",
        "family": "Long March 4",
        "full_name": "Long March 4C",
        "variant": "C"
    }
    },
        "mission": {
        "id": 6488,
        "name": "Fengyun-3F",
        "description": "The newer FY-3 series is an improved generation of polar-orbiting heliosynchronous weather satellites.",
        "launch_designator": null,
        "type": "Earth Science",
        "orbit": {
        "id": 17,
        "name": "Sun-Synchronous Orbit",
        "abbrev": "SSO"
    }
    },
        "pad": {
        "id": 22,
        "url": "https://lldev.thespacedevs.com/2.2.0/pad/22/",
        "agency_id": null,
        "name": "Launch Area 4 (SLS-2 / 603)",
        "info_url": null,
        "wiki_url": "https://en.wikipedia.org/wiki/Jiuquan_Launch_Area_4",
        "map_url": "https://www.google.com/maps?q=40.960556,100.298333",
        "latitude": "40.960556",
        "longitude": "100.298333",
        "location": {
        "id": 17,
        "url": "https://lldev.thespacedevs.com/2.2.0/location/17/",
        "name": "Jiuquan Satellite Launch Center, People's Republic of China",
        "country_code": "CHN",
        "map_image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launch_images/location_17_20200803142429.jpg",
        "timezone_name": "Asia/Shanghai",
        "total_launch_count": 200,
        "total_landing_count": 0
    },
        "country_code": "CHN",
        "map_image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launch_images/pad_22_20200803143437.jpg",
        "total_launch_count": 103,
        "orbital_launch_attempt_count": 103
    },
        "webcast_live": false,
        "image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launcher_images/long2520march25204_image_20190520170012.jpeg",
        "infographic": null,
        "program": [],
        "orbital_launch_attempt_count": 6475,
        "location_launch_attempt_count": 201,
        "pad_launch_attempt_count": 104,
        "agency_launch_attempt_count": 430,
        "orbital_launch_attempt_count_year": 117,
        "location_launch_attempt_count_year": 19,
        "pad_launch_attempt_count_year": 8,
        "agency_launch_attempt_count_year": 23
    },
    {
        "id": "afc772a3-6ea7-4550-a4e9-35c70c22ebba",
        "url": "https://lldev.thespacedevs.com/2.2.0/launch/afc772a3-6ea7-4550-a4e9-35c70c22ebba/",
        "slug": "falcon-9-block-5-galaxy-37",
        "name": "Falcon 9 Block 5 | Galaxy 37",
        "status": {
        "id": 8,
        "name": "To Be Confirmed",
        "abbrev": "TBC",
        "description": "Awaiting official confirmation - current date is known with some certainty."
    },
        "last_updated": "2023-07-29T16:02:35Z",
        "net": "2023-08-03T04:15:00Z",
        "window_end": "2023-08-03T06:55:00Z",
        "window_start": "2023-08-03T04:05:00Z",
        "net_precision": {
        "id": 1,
        "name": "Minute",
        "abbrev": "MIN",
        "description": "The T-0 is accurate to the minute."
    },
        "probability": null,
        "weather_concerns": null,
        "holdreason": "",
        "failreason": "",
        "hashtag": null,
        "launch_service_provider": {
        "id": 121,
        "url": "https://lldev.thespacedevs.com/2.2.0/agencies/121/",
        "name": "SpaceX",
        "type": "Commercial"
    },
        "rocket": {
        "id": 7550,
        "configuration": {
        "id": 164,
        "url": "https://lldev.thespacedevs.com/2.2.0/config/launcher/164/",
        "name": "Falcon 9",
        "family": "Falcon",
        "full_name": "Falcon 9 Block 5",
        "variant": "Block 5"
    }
    },
        "mission": {
        "id": 5977,
        "name": "Galaxy 37",
        "description": "Galaxy 37 is a geostationary communications satellite manufactured by Maxar and operated by Intelsat.",
        "launch_designator": null,
        "type": "Communications",
        "orbit": {
        "id": 2,
        "name": "Geostationary Transfer Orbit",
        "abbrev": "GTO"
    }
    },
        "pad": {
        "id": 80,
        "url": "https://lldev.thespacedevs.com/2.2.0/pad/80/",
        "agency_id": 121,
        "name": "Space Launch Complex 40",
        "info_url": null,
        "wiki_url": "https://en.wikipedia.org/wiki/Cape_Canaveral_Air_Force_Station_Space_Launch_Complex_40",
        "map_url": "https://www.google.com/maps?q=28.56194122,-80.57735736",
        "latitude": "28.56194122",
        "longitude": "-80.57735736",
        "location": {
        "id": 12,
        "url": "https://lldev.thespacedevs.com/2.2.0/location/12/",
        "name": "Cape Canaveral, FL, USA",
        "country_code": "USA",
        "map_image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launch_images/location_12_20200803142519.jpg",
        "timezone_name": "America/New_York",
        "total_launch_count": 898,
        "total_landing_count": 36
    },
        "country_code": "USA",
        "map_image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launch_images/pad_80_20200803143323.jpg",
        "total_launch_count": 191,
        "orbital_launch_attempt_count": 191
    },
        "webcast_live": false,
        "image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launcher_images/falcon_9_block__image_20210506060831.jpg",
        "infographic": null,
        "program": [],
        "orbital_launch_attempt_count": 6476,
        "location_launch_attempt_count": 899,
        "pad_launch_attempt_count": 192,
        "agency_launch_attempt_count": 266,
        "orbital_launch_attempt_count_year": 118,
        "location_launch_attempt_count_year": 31,
        "pad_launch_attempt_count_year": 29,
        "agency_launch_attempt_count_year": 53
    },
    {
        "id": "e694f57c-dba0-44ed-9c7b-abbeab437382",
        "url": "https://lldev.thespacedevs.com/2.2.0/launch/e694f57c-dba0-44ed-9c7b-abbeab437382/",
        "slug": "soyuz-21bfregat-m-glonass-k2-no-13",
        "name": "Soyuz 2.1b/Fregat-M | Glonass-K2 No. 13",
        "status": {
        "id": 8,
        "name": "To Be Confirmed",
        "abbrev": "TBC",
        "description": "Awaiting official confirmation - current date is known with some certainty."
    },
        "last_updated": "2023-07-27T17:08:21Z",
        "net": "2023-08-07T12:00:00Z",
        "window_end": "2023-08-07T15:00:00Z",
        "window_start": "2023-08-07T12:00:00Z",
        "net_precision": {
        "id": 2,
        "name": "Hour",
        "abbrev": "HR",
        "description": "The T-0 is accurate to the hour."
    },
        "probability": null,
        "weather_concerns": null,
        "holdreason": "",
        "failreason": "",
        "hashtag": null,
        "launch_service_provider": {
        "id": 193,
        "url": "https://lldev.thespacedevs.com/2.2.0/agencies/193/",
        "name": "Russian Space Forces",
        "type": "Government"
    },
        "rocket": {
        "id": 7966,
        "configuration": {
        "id": 134,
        "url": "https://lldev.thespacedevs.com/2.2.0/config/launcher/134/",
        "name": "Soyuz 2.1b/Fregat-M",
        "family": "Soyuz",
        "full_name": "Soyuz 2.1b Fregat-M",
        "variant": "Fregat-M"
    }
    },
        "mission": {
        "id": 6483,
        "name": "Glonass-K2 No. 13",
        "description": "NOTE: Payload identity unconfirmed.\r\n\r\nGlonass-K2 are the fourth generation of satellite design for GLONASS satellite navigation system. GLONASS is a Russian space-based navigation system comparable to the similar GPS and Galileo systems. This generation improves on accuracy, power consumption and design life. Each satellite is unpressurized and weighs 1645 kg,  and has an operational lifetime of 10 years.",
        "launch_designator": null,
        "type": "Navigation",
        "orbit": {
        "id": 12,
        "name": "Medium Earth Orbit",
        "abbrev": "MEO"
    }
    },
        "pad": {
        "id": 85,
        "url": "https://lldev.thespacedevs.com/2.2.0/pad/85/",
        "agency_id": 163,
        "name": "43/4 (43R)",
        "info_url": null,
        "wiki_url": "",
        "map_url": "https://www.google.com/maps?q=62.92883,40.457098",
        "latitude": "62.92883",
        "longitude": "40.457098",
        "location": {
        "id": 6,
        "url": "https://lldev.thespacedevs.com/2.2.0/location/6/",
        "name": "Plesetsk Cosmodrome, Russian Federation",
        "country_code": "RUS",
        "map_image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launch_images/location_6_20200803142434.jpg",
        "timezone_name": "Europe/Moscow",
        "total_launch_count": 1661,
        "total_landing_count": 0
    },
        "country_code": "RUS",
        "map_image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launch_images/pad_85_20200803143554.jpg",
        "total_launch_count": 315,
        "orbital_launch_attempt_count": 315
    },
        "webcast_live": false,
        "image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launcher_images/soyuz25202.1b_image_20190520165337.jpg",
        "infographic": null,
        "program": [],
        "orbital_launch_attempt_count": 6477,
        "location_launch_attempt_count": 1662,
        "pad_launch_attempt_count": 316,
        "agency_launch_attempt_count": 133,
        "orbital_launch_attempt_count_year": 119,
        "location_launch_attempt_count_year": 3,
        "pad_launch_attempt_count_year": 2,
        "agency_launch_attempt_count_year": 3
    },
    {
        "id": "6229654f-e7ea-4d97-80f7-0195161e8645",
        "url": "https://lldev.thespacedevs.com/2.2.0/launch/6229654f-e7ea-4d97-80f7-0195161e8645/",
        "slug": "spaceshiptwo-galactic-02",
        "name": "SpaceShipTwo | Galactic 02",
        "status": {
        "id": 8,
        "name": "To Be Confirmed",
        "abbrev": "TBC",
        "description": "Awaiting official confirmation - current date is known with some certainty."
    },
        "last_updated": "2023-07-26T09:54:48Z",
        "net": "2023-08-10T14:00:00Z",
        "window_end": "2023-08-10T22:00:00Z",
        "window_start": "2023-08-10T14:00:00Z",
        "net_precision": {
        "id": 2,
        "name": "Hour",
        "abbrev": "HR",
        "description": "The T-0 is accurate to the hour."
    },
        "probability": null,
        "weather_concerns": null,
        "holdreason": "",
        "failreason": "",
        "hashtag": null,
        "launch_service_provider": {
        "id": 1024,
        "url": "https://lldev.thespacedevs.com/2.2.0/agencies/1024/",
        "name": "Virgin Galactic",
        "type": "Private"
    },
        "rocket": {
        "id": 7906,
        "configuration": {
        "id": 465,
        "url": "https://lldev.thespacedevs.com/2.2.0/config/launcher/465/",
        "name": "SpaceShipTwo",
        "family": "SpaceShipTwo",
        "full_name": "SpaceShipTwo",
        "variant": ""
    }
    },
        "mission": {
        "id": 6413,
        "name": "Galactic 02",
        "description": "Second commercial Virgin Galactic mission.",
        "launch_designator": null,
        "type": "Tourism",
        "orbit": {
        "id": 15,
        "name": "Suborbital",
        "abbrev": "Sub"
    }
    },
        "pad": {
        "id": 191,
        "url": "https://lldev.thespacedevs.com/2.2.0/pad/191/",
        "agency_id": 1024,
        "name": "Spaceport America",
        "info_url": "https://www.spaceportamerica.com/",
        "wiki_url": "https://en.wikipedia.org/wiki/Spaceport_America",
        "map_url": "https://www.google.com/maps?q=32.9902778,-106.9719162",
        "latitude": "32.9902778",
        "longitude": "-106.9719162",
        "location": {
        "id": 144,
        "url": "https://lldev.thespacedevs.com/2.2.0/location/144/",
        "name": "Air launch to Suborbital flight",
        "country_code": "",
        "map_image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launch_images/location_144_20200803142439.jpg",
        "timezone_name": "",
        "total_launch_count": 80,
        "total_landing_count": 0
    },
        "country_code": "USA",
        "map_image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launch_images/pad_spaceport_america_20210522162030.jpg",
        "total_launch_count": 8,
        "orbital_launch_attempt_count": 0
    },
        "webcast_live": false,
        "image": "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launcher_images/spaceshiptwo_image_20210522140909.jpeg",
        "infographic": null,
        "program": [],
        "orbital_launch_attempt_count": null,
        "location_launch_attempt_count": 81,
        "pad_launch_attempt_count": 9,
        "agency_launch_attempt_count": 62,
        "orbital_launch_attempt_count_year": 0,
        "location_launch_attempt_count_year": 4,
        "pad_launch_attempt_count_year": 4,
        "agency_launch_attempt_count_year": 4
    }
    ]
}"""

const val launchDetailDtoString = """{
	"id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
	"url": "string",
	"slug": "YfTfX5lRvoqY05CvNqid6VR-QcxcGR1j0XHbxNi9kwXE5vwzY44hyjWAKrqC",
	"flightclub_url": "string",
	"r_spacex_api_id": "string",
	"name": "string",
	"status": {
		"id": 2147483647,
		"name": "string",
		"abbrev": "string",
		"description": "string"
	},
	"last_updated": "2023-08-03T06:55:52.668Z",
	"updates": [{
		"id": 0,
		"profile_image": "string",
		"comment": "string",
		"info_url": "string",
		"created_by": "string",
		"created_on": "2023-08-03T06:55:52.668Z"
	}],
	"net": "2023-08-03T06:55:52.668Z",
	"net_precision": {
		"id": 2147483647,
		"name": "string",
		"abbrev": "string",
		"description": "string"
	},
	"window_end": "2023-08-03T06:55:52.668Z",
	"window_start": "2023-08-03T06:55:52.668Z",
	"probability": 0,
	"weather_concerns": "string",
	"holdreason": "string",
	"failreason": "string",
	"hashtag": "string",
	"launch_service_provider": {
		"id": 0,
		"url": "string",
		"name": "string",
		"featured": true,
		"type": "string",
		"country_code": "string",
		"abbrev": "string",
		"description": "string",
		"administrator": "string",
		"founding_year": "string",
		"launchers": "string",
		"spacecraft": "string",
		"launch_library_url": "string",
		"total_launch_count": 2147483647,
		"consecutive_successful_launches": 2147483647,
		"successful_launches": 2147483647,
		"failed_launches": 2147483647,
		"pending_launches": 2147483647,
		"consecutive_successful_landings": 2147483647,
		"successful_landings": 2147483647,
		"failed_landings": 2147483647,
		"attempted_landings": 2147483647,
		"info_url": "string",
		"wiki_url": "string",
		"logo_url": "string",
		"image_url": "string",
		"nation_url": "string"
	},
	"rocket": {
		"id": 0,
		"configuration": {
			"id": 0,
			"url": "string",
			"name": "string",
			"active": true,
			"reusable": true,
			"description": "string",
			"family": "string",
			"full_name": "string",
			"manufacturer": {
				"id": 0,
				"url": "string",
				"name": "string",
				"featured": true,
				"type": "string",
				"country_code": "string",
				"abbrev": "string",
				"description": "string",
				"administrator": "string",
				"founding_year": "string",
				"launchers": "string",
				"spacecraft": "string",
				"launch_library_url": "string",
				"total_launch_count": 2147483647,
				"consecutive_successful_launches": 2147483647,
				"successful_launches": 2147483647,
				"failed_launches": 2147483647,
				"pending_launches": 2147483647,
				"consecutive_successful_landings": 2147483647,
				"successful_landings": 2147483647,
				"failed_landings": 2147483647,
				"attempted_landings": 2147483647,
				"info_url": "string",
				"wiki_url": "string",
				"logo_url": "string",
				"image_url": "string",
				"nation_url": "string"
			},
			"program": [{
				"id": 0,
				"url": "string",
				"name": "string",
				"description": "string",
				"agencies": [{
					"id": 0,
					"url": "string",
					"name": "string",
					"type": "string"
				}],
				"image_url": "string",
				"start_date": "2023-08-03T06:55:52.668Z",
				"end_date": "2023-08-03T06:55:52.668Z",
				"info_url": "string",
				"wiki_url": "string",
				"mission_patches": [{
					"id": 0,
					"name": "string",
					"priority": 2147483647,
					"image_url": "string",
					"agency": {
						"id": 0,
						"url": "string",
						"name": "string",
						"type": "string"
					}
				}]
			}],
			"variant": "string",
			"alias": "string",
			"min_stage": 2147483647,
			"max_stage": 2147483647,
			"length": 0,
			"diameter": 0,
			"maiden_flight": "2023-08-03",
			"launch_cost": "string",
			"launch_mass": 2147483647,
			"leo_capacity": 2147483647,
			"gto_capacity": 2147483647,
			"to_thrust": 2147483647,
			"apogee": 2147483647,
			"vehicle_range": 2147483647,
			"image_url": "string",
			"info_url": "string",
			"wiki_url": "string",
			"total_launch_count": 2147483647,
			"consecutive_successful_launches": 2147483647,
			"successful_launches": 2147483647,
			"failed_launches": 2147483647,
			"pending_launches": 2147483647,
			"attempted_landings": 2147483647,
			"successful_landings": 2147483647,
			"failed_landings": 2147483647,
			"consecutive_successful_landings": 2147483647
		},
		"launcher_stage": [{
			"id": 0,
			"type": "string",
			"reused": true,
			"launcher_flight_number": 2147483647,
			"launcher": {
				"id": 0,
				"url": "string",
				"details": "string",
				"flight_proven": true,
				"serial_number": "string",
				"status": "string",
				"image_url": "string",
				"successful_landings": 2147483647,
				"attempted_landings": 2147483647,
				"flights": 2147483647,
				"last_launch_date": "2023-08-03T06:55:52.668Z",
				"first_launch_date": "2023-08-03T06:55:52.668Z"
			},
			"landing": {
				"id": 0,
				"attempt": true,
				"success": true,
				"description": "string",
				"downrange_distance": 2147483647,
				"location": {
					"id": 0,
					"name": "string",
					"abbrev": "string",
					"description": "string",
					"location": {
						"id": 0,
						"url": "string",
						"name": "string",
						"country_code": "string",
						"map_image": "string",
						"timezone_name": "string",
						"total_launch_count": 2147483647,
						"total_landing_count": 2147483647
					},
					"successful_landings": "Unknown Type: int"
				},
				"type": {
					"id": 0,
					"name": "string",
					"abbrev": "string",
					"description": "string"
				}
			},
			"previous_flight_date": "2023-08-03T06:55:52.668Z",
			"turn_around_time_days": 2147483647,
			"previous_flight": {
				"id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
				"name": "string"
			}
		}],
		"spacecraft_stage": {
			"id": 0,
			"url": "string",
			"mission_end": "2023-08-03T06:55:52.668Z",
			"destination": "string",
			"launch_crew": [{
				"id": 0,
				"role": {
					"id": 0,
					"role": "string",
					"priority": 2147483647
				},
				"astronaut": {
					"id": 0,
					"url": "string",
					"name": "string",
					"type": {
						"id": 0,
						"name": "string"
					},
					"in_space": true,
					"time_in_space": "string",
					"status": {
						"id": 0,
						"name": "string"
					},
					"agency": {
						"id": 0,
						"url": "string",
						"name": "string",
						"type": "string"
					},
					"date_of_birth": "2023-08-03",
					"date_of_death": "2023-08-03",
					"nationality": "string",
					"twitter": "string",
					"instagram": "string",
					"bio": "string",
					"profile_image": "string",
					"wiki": "string",
					"last_flight": "2023-08-03T06:55:52.668Z",
					"first_flight": "2023-08-03T06:55:52.668Z"
				}
			}],
			"onboard_crew": [{
				"id": 0,
				"role": {
					"id": 0,
					"role": "string",
					"priority": 2147483647
				},
				"astronaut": {
					"id": 0,
					"url": "string",
					"name": "string",
					"type": {
						"id": 0,
						"name": "string"
					},
					"in_space": true,
					"time_in_space": "string",
					"status": {
						"id": 0,
						"name": "string"
					},
					"agency": {
						"id": 0,
						"url": "string",
						"name": "string",
						"type": "string"
					},
					"date_of_birth": "2023-08-03",
					"date_of_death": "2023-08-03",
					"nationality": "string",
					"twitter": "string",
					"instagram": "string",
					"bio": "string",
					"profile_image": "string",
					"wiki": "string",
					"last_flight": "2023-08-03T06:55:52.668Z",
					"first_flight": "2023-08-03T06:55:52.668Z"
				}
			}],
			"landing_crew": [{
				"id": 0,
				"role": {
					"id": 0,
					"role": "string",
					"priority": 2147483647
				},
				"astronaut": {
					"id": 0,
					"url": "string",
					"name": "string",
					"type": {
						"id": 0,
						"name": "string"
					},
					"in_space": true,
					"time_in_space": "string",
					"status": {
						"id": 0,
						"name": "string"
					},
					"agency": {
						"id": 0,
						"url": "string",
						"name": "string",
						"type": "string"
					},
					"date_of_birth": "2023-08-03",
					"date_of_death": "2023-08-03",
					"nationality": "string",
					"twitter": "string",
					"instagram": "string",
					"bio": "string",
					"profile_image": "string",
					"wiki": "string",
					"last_flight": "2023-08-03T06:55:52.668Z",
					"first_flight": "2023-08-03T06:55:52.668Z"
				}
			}],
			"spacecraft": {
				"id": 0,
				"url": "string",
				"name": "string",
				"serial_number": "string",
				"is_placeholder": true,
				"in_space": true,
				"time_in_space": "string",
				"time_docked": "string",
				"flights_count": 0,
				"mission_ends_count": 2147483647,
				"status": {
					"id": 0,
					"name": "string"
				},
				"description": "string",
				"spacecraft_config": {
					"id": 0,
					"url": "string",
					"name": "string",
					"type": {
						"id": 0,
						"name": "string"
					},
					"agency": {
						"id": 0,
						"url": "string",
						"name": "string",
						"featured": true,
						"type": "string",
						"country_code": "string",
						"abbrev": "string",
						"description": "string",
						"administrator": "string",
						"founding_year": "string",
						"launchers": "string",
						"spacecraft": "string",
						"parent": "string",
						"image_url": "string",
						"logo_url": "string"
					},
					"in_use": true,
					"capability": "string",
					"history": "string",
					"details": "string",
					"maiden_flight": "2023-08-03",
					"height": 0,
					"diameter": 0,
					"human_rated": true,
					"crew_capacity": 2147483647,
					"payload_capacity": 2147483647,
					"flight_life": "string",
					"image_url": "string",
					"nation_url": "string",
					"wiki_link": "string",
					"info_link": "string"
				}
			},
			"landing": {
				"id": 0,
				"attempt": true,
				"success": true,
				"description": "string",
				"downrange_distance": 2147483647,
				"location": {
					"id": 0,
					"name": "string",
					"abbrev": "string",
					"description": "string",
					"location": {
						"id": 0,
						"url": "string",
						"name": "string",
						"country_code": "string",
						"map_image": "string",
						"timezone_name": "string",
						"total_launch_count": 2147483647,
						"total_landing_count": 2147483647
					},
					"successful_landings": "Unknown Type: int"
				},
				"type": {
					"id": 0,
					"name": "string",
					"abbrev": "string",
					"description": "string"
				}
			},
			"docking_events": [{
				"spacestation": {
					"id": 0,
					"url": "string",
					"name": "string",
					"status": {
						"id": 0,
						"name": "string"
					},
					"founded": "2023-08-03",
					"description": "string",
					"orbit": "string",
					"image_url": "string"
				},
				"docking": "2023-08-03T06:55:52.669Z",
				"departure": "2023-08-03T06:55:52.669Z",
				"docking_location": {
					"id": 0,
					"name": "string",
					"spacestation": {
						"id": 0,
						"url": "string",
						"name": "string"
					}
				}
			}]
		}
	},
	"mission": {
		"id": 0,
		"name": "string",
		"description": "string",
		"launch_designator": "string",
		"type": "string",
		"orbit": {
			"id": 0,
			"name": "string",
			"abbrev": "string"
		}
	},
	"pad": {
		"id": 0,
		"url": "string",
		"agency_id": 2147483647,
		"name": "string",
		"info_url": "string",
		"wiki_url": "string",
		"map_url": "string",
		"latitude": "string",
		"longitude": "string",
		"location": {
			"id": 0,
			"url": "string",
			"name": "string",
			"country_code": "string",
			"map_image": "string",
			"timezone_name": "string",
			"total_launch_count": 2147483647,
			"total_landing_count": 2147483647
		},
		"country_code": "string",
		"map_image": "string",
		"total_launch_count": 2147483647,
		"orbital_launch_attempt_count": 2147483647
	},
	"infoURLs": [{
		"priority": 2147483647,
		"title": "string",
		"description": "string",
		"feature_image": "string",
		"url": "string"
	}],
	"vidURLs": [{
		"priority": 2147483647,
		"title": "string",
		"description": "string",
		"feature_image": "string",
		"url": "string"
	}],
	"webcast_live": true,
	"image": "string",
	"infographic": "string",
	"program": [{
		"id": 0,
		"url": "string",
		"name": "string",
		"description": "string",
		"agencies": [{
			"id": 0,
			"url": "string",
			"name": "string",
			"type": "string"
		}],
		"image_url": "string",
		"start_date": "2023-08-03T06:55:52.669Z",
		"end_date": "2023-08-03T06:55:52.669Z",
		"info_url": "string",
		"wiki_url": "string",
		"mission_patches": [{
			"id": 0,
			"name": "string",
			"priority": 2147483647,
			"image_url": "string",
			"agency": {
				"id": 0,
				"url": "string",
				"name": "string",
				"type": "string"
			}
		}]
	}],
	"orbital_launch_attempt_count": 0,
	"location_launch_attempt_count": 0,
	"pad_launch_attempt_count": 0,
	"agency_launch_attempt_count": 0,
	"orbital_launch_attempt_count_year": 0,
	"location_launch_attempt_count_year": 0,
	"pad_launch_attempt_count_year": 0,
	"agency_launch_attempt_count_year": 0,
	"pad_turnaround": "string",
	"mission_patches": [{
		"id": 0,
		"name": "string",
		"priority": 2147483647,
		"image_url": "string",
		"agency": {
			"id": 0,
			"url": "string",
			"name": "string",
			"type": "string"
		}
	}]
}
"""
