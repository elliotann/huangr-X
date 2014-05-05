<?xml version="1.0" encoding="UTF-8"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:activiti="http://activiti.org/bpmn"
             xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI"
             xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC"
             xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI" typeLanguage="http://www.w3.org/2001/XMLSchema"
             expressionLanguage="http://www.w3.org/1999/XPath" targetNamespace="http://www.easysoft.jeap.com/activiti/${processKey}">
    <process id="${processKey}" name="${processName}">
        <documentation>${displayName}</documentation>
        <#list flowDefTlp.nodes as item>
            <#if item.nodeType=="start">
                <startEvent id="${item.props['key']}" name="${item.props['name']}" activiti:initiator="${item.props['initiator']}"></startEvent>
            </#if>
            <#if item.nodeType=="task">
                <userTask id="deptLeaderAudit" name="部门领导审批" activiti:candidateGroups="deptLeader"></userTask>
            </#if>
            <#if item.nodeType=="end">
                <endEvent id="endevent1" name="End"></endEvent>
            </#if>
        </#list>





        <sequenceFlow id="flow2" name="" sourceRef="startevent1" targetRef="deptLeaderAudit"></sequenceFlow>
        <sequenceFlow id="flow3" name="" sourceRef="deptLeaderAudit" targetRef="exclusivegateway5"></sequenceFlow>
        <sequenceFlow id="flow4" name="不同意" sourceRef="exclusivegateway5" targetRef="modifyApply">
            <conditionExpression xsi:type="tFormalExpression"></conditionExpression>
        </sequenceFlow>
        <sequenceFlow id="flow5" name="同意" sourceRef="exclusivegateway5" targetRef="hrAudit">
            <conditionExpression xsi:type="tFormalExpression"></conditionExpression>
        </sequenceFlow>
        <sequenceFlow id="flow6" name="" sourceRef="hrAudit" targetRef="exclusivegateway6"></sequenceFlow>
        <sequenceFlow id="flow7" name="同意" sourceRef="exclusivegateway6" targetRef="reportBack">
            <conditionExpression xsi:type="tFormalExpression"></conditionExpression>
        </sequenceFlow>
        <sequenceFlow id="flow8" name="" sourceRef="reportBack" targetRef="endevent1"></sequenceFlow>
        <sequenceFlow id="flow9" name="不同意" sourceRef="exclusivegateway6" targetRef="modifyApply">
            <conditionExpression xsi:type="tFormalExpression"></conditionExpression>
        </sequenceFlow>
        <sequenceFlow id="flow10" name="重新申请" sourceRef="exclusivegateway7" targetRef="deptLeaderAudit">
            <conditionExpression xsi:type="tFormalExpression"></conditionExpression>
        </sequenceFlow>
        <sequenceFlow id="flow11" name="" sourceRef="modifyApply" targetRef="exclusivegateway7"></sequenceFlow>
        <sequenceFlow id="flow12" name="结束流程" sourceRef="exclusivegateway7" targetRef="endevent1">
            <conditionExpression xsi:type="tFormalExpression"></conditionExpression>
        </sequenceFlow>
    </process>
    <bpmndi:BPMNDiagram id="BPMNDiagram_${processKey}">
        <bpmndi:BPMNPlane bpmnElement="${processKey}" id="BPMNPlane_${processKey}">
        <#list flowDefTlp.nodes as item>
            <bpmndi:BPMNShape bpmnElement="${item.props['key']}" id="BPMNShape_${item.props['key']}">
                <omgdc:Bounds height="${item.height}" width="${item.width}" x="${item.x}" y="${item.y}"></omgdc:Bounds>
            </bpmndi:BPMNShape>
        </#list>


            <bpmndi:BPMNEdge bpmnElement="flow2" id="BPMNEdge_flow2">
                <omgdi:waypoint x="45" y="107"></omgdi:waypoint>
                <omgdi:waypoint x="90" y="107"></omgdi:waypoint>
            </bpmndi:BPMNEdge>
            <bpmndi:BPMNEdge bpmnElement="flow3" id="BPMNEdge_flow3">
                <omgdi:waypoint x="195" y="107"></omgdi:waypoint>
                <omgdi:waypoint x="250" y="107"></omgdi:waypoint>
            </bpmndi:BPMNEdge>
            <bpmndi:BPMNEdge bpmnElement="flow4" id="BPMNEdge_flow4">
                <omgdi:waypoint x="270" y="127"></omgdi:waypoint>
                <omgdi:waypoint x="270" y="190"></omgdi:waypoint>
                <bpmndi:BPMNLabel>
                    <omgdc:Bounds height="11" width="100" x="10" y="0"></omgdc:Bounds>
                </bpmndi:BPMNLabel>
            </bpmndi:BPMNEdge>
            <bpmndi:BPMNEdge bpmnElement="flow5" id="BPMNEdge_flow5">
                <omgdi:waypoint x="290" y="107"></omgdi:waypoint>
                <omgdi:waypoint x="358" y="107"></omgdi:waypoint>
                <bpmndi:BPMNLabel>
                    <omgdc:Bounds height="11" width="100" x="-24" y="-17"></omgdc:Bounds>
                </bpmndi:BPMNLabel>
            </bpmndi:BPMNEdge>
            <bpmndi:BPMNEdge bpmnElement="flow6" id="BPMNEdge_flow6">
                <omgdi:waypoint x="463" y="107"></omgdi:waypoint>
                <omgdi:waypoint x="495" y="107"></omgdi:waypoint>
            </bpmndi:BPMNEdge>
            <bpmndi:BPMNEdge bpmnElement="flow7" id="BPMNEdge_flow7">
                <omgdi:waypoint x="535" y="107"></omgdi:waypoint>
                <omgdi:waypoint x="590" y="107"></omgdi:waypoint>
                <bpmndi:BPMNLabel>
                    <omgdc:Bounds height="11" width="100" x="-22" y="-17"></omgdc:Bounds>
                </bpmndi:BPMNLabel>
            </bpmndi:BPMNEdge>
            <bpmndi:BPMNEdge bpmnElement="flow8" id="BPMNEdge_flow8">
                <omgdi:waypoint x="642" y="135"></omgdi:waypoint>
                <omgdi:waypoint x="642" y="283"></omgdi:waypoint>
            </bpmndi:BPMNEdge>
            <bpmndi:BPMNEdge bpmnElement="flow9" id="BPMNEdge_flow9">
                <omgdi:waypoint x="515" y="127"></omgdi:waypoint>
                <omgdi:waypoint x="514" y="217"></omgdi:waypoint>
                <omgdi:waypoint x="323" y="217"></omgdi:waypoint>
                <bpmndi:BPMNLabel>
                    <omgdc:Bounds height="11" width="100" x="10" y="0"></omgdc:Bounds>
                </bpmndi:BPMNLabel>
            </bpmndi:BPMNEdge>
            <bpmndi:BPMNEdge bpmnElement="flow10" id="BPMNEdge_flow10">
                <omgdi:waypoint x="250" y="300"></omgdi:waypoint>
                <omgdi:waypoint x="142" y="299"></omgdi:waypoint>
                <omgdi:waypoint x="142" y="135"></omgdi:waypoint>
                <bpmndi:BPMNLabel>
                    <omgdc:Bounds height="11" width="100" x="10" y="0"></omgdc:Bounds>
                </bpmndi:BPMNLabel>
            </bpmndi:BPMNEdge>
            <bpmndi:BPMNEdge bpmnElement="flow11" id="BPMNEdge_flow11">
                <omgdi:waypoint x="270" y="245"></omgdi:waypoint>
                <omgdi:waypoint x="270" y="280"></omgdi:waypoint>
            </bpmndi:BPMNEdge>
            <bpmndi:BPMNEdge bpmnElement="flow12" id="BPMNEdge_flow12">
                <omgdi:waypoint x="290" y="300"></omgdi:waypoint>
                <omgdi:waypoint x="625" y="300"></omgdi:waypoint>
                <bpmndi:BPMNLabel>
                    <omgdc:Bounds height="11" width="100" x="10" y="0"></omgdc:Bounds>
                </bpmndi:BPMNLabel>
            </bpmndi:BPMNEdge>
        </bpmndi:BPMNPlane>
    </bpmndi:BPMNDiagram>
</definitions>